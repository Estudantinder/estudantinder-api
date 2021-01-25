package org.estudantinder.features.Users.CreateUser;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.BadRequestException;

import org.estudantinder.entities.Contacts;
import org.estudantinder.entities.Course;
import org.estudantinder.entities.Preferences;
import org.estudantinder.entities.Users;
import org.estudantinder.features.Users.CreateUser.DTO.ContactsDTO;
import org.estudantinder.features.Users.CreateUser.DTO.PreferencesDTO;
import org.estudantinder.features.Users.CreateUser.DTO.UserDTO;
import org.estudantinder.entities.Subject;

import org.estudantinder.repositories.CoursesRepository;
import org.estudantinder.repositories.UsersRepository;
import org.estudantinder.repositories.SubjectsRepository;

@ApplicationScoped
public class Feature {

    @Inject
    UsersRepository UsersRepository;
    
    @Inject
    CoursesRepository coursesRepository;
    
    @Inject
    SubjectsRepository subjectsRepository;

    public boolean checkIfAgeIsntCorrect(LocalDate birth_date) {
        LocalDate today = LocalDate.now();
        int UserAge = Period.between(birth_date, today).getYears();

        if(UserAge < 14 || UserAge > 21) {
            return true;
        }

        return false;
    }
    
    public boolean checkIfPasswordDoesntContainsNumber(String password) {
        //code reference https://stackoverflow.com/questions/18590901/check-if-a-string-contains-numbers-java#18590949
        if(password.matches(".*\\d.*")) {
            return false;
        }

        return true;
    }

    public boolean checkIfClassroomIsAlphabetical(char classroom) {
        if((classroom >= 'a' && classroom <= 'z') || (classroom >= 'A' && classroom <= 'Z')) {
            return false;
        }

        return true;
    }

    public boolean checkIfTwitterStartsWithAt(String socialMedia) {
        if(!(socialMedia.charAt(0) == '@')) {
            return true;
        }

        return false;
    }

    public Course returnCourseIfExists(Long courseId) {
        Course course = coursesRepository.findById(courseId);

        if(course == null) {
            throw new EntityNotFoundException("Course Not Found");
        }

        return course;
    }

    public boolean isListFieldDuplicate(List<Long> subjects_id) {
        final Set<Long> uniqueIds = new HashSet<>();
        
        for (Long subject_id : subjects_id){
            if (!uniqueIds.add(subject_id)){
                return true;
            }
        }
        
        return false;
    }

    public Subject returnSubjectIfExists(Long subject_id) {
        Subject subject = subjectsRepository.findById(subject_id);

        if(subject == null) {
            throw new EntityNotFoundException("Subject id "+subject_id+" Not Found");
        }

        return subject;
    }

    public List<Subject> returnListOfSubjects(List<Long> subjects_id) {
        List<Subject> subjects = new ArrayList<Subject>();
        
        if(isListFieldDuplicate(subjects_id)) {
            throw new BadRequestException("Subject ID's can't be duplicated");
        }

        subjects_id.forEach(subject_id -> {
            Subject subject = returnSubjectIfExists(subject_id);
            subjects.add(subject);
        });

        return subjects;
    } 

    public Preferences setNewUserPreferences(PreferencesDTO preferences) {
        Preferences newUserPreferences = new Preferences();
        
        newUserPreferences.setGender(preferences.gender);
        newUserPreferences.setShift(preferences.shift);
        newUserPreferences.setSchool_year(preferences.school_year);
        if (preferences.course_id != null) {
            newUserPreferences.setCourse(returnCourseIfExists(preferences.course_id));
        }

        return newUserPreferences;
    }

    public Contacts setNewStudenContacts(ContactsDTO contacts) {
        Contacts newUserContacts = new Contacts();

        newUserContacts.setWhatsapp(contacts.whatsapp);
        newUserContacts.setFacebook(contacts.facebook);
        newUserContacts.setInstagram(contacts.instagram);
        newUserContacts.setTwitter(contacts.twitter);

        return newUserContacts;
    }

    public Users setNewUser(UserDTO User) {
        Users newUser = new Users();

        newUser.setName(User.name);
        newUser.setEmail(User.email);
        newUser.setPassword(User.password.trim());
        newUser.setSchool_year(User.school_year);
        newUser.setBirth_date(User.birth_date);
        newUser.setBio(User.bio);
        newUser.setGender(User.gender);
        newUser.setShift(User.shift);
        newUser.setClassroom(Character.toUpperCase(User.classroom));
        newUser.setPhotos(User.photos);
        newUser.setSubjects(returnListOfSubjects(User.subjects_id));
        newUser.setCourse(returnCourseIfExists(User.course_id));
        newUser.setContacts(setNewStudenContacts(User.contacts));
        
        if(User.preferences != null) {
            newUser.setPreferences(setNewUserPreferences(User.preferences));
        }

        return newUser;
    }

    public void execute(UserDTO data) throws Exception {
        boolean isEmailAlreadyInUse = UsersRepository.isEmailAlreadyInUse(data.email);

        if(isEmailAlreadyInUse) {
            throw new EntityExistsException("Email Already In Use");
        }
   
        if(data.contacts == null) {
            throw new EntityNotFoundException("At Least 1 Contact is Required");
        }

        if(checkIfPasswordDoesntContainsNumber(data.password)) {
            throw new BadRequestException("Password must contain at least 1 number");
        }

        if(checkIfAgeIsntCorrect(data.birth_date)) {
            throw new BadRequestException("User age must be between 14-21");
        }

        if(checkIfClassroomIsAlphabetical(data.classroom)) {
            throw new BadRequestException("Classroom must be alphabetical");
        }
        
        if(data.contacts.twitter != null) {
            if(checkIfTwitterStartsWithAt(data.contacts.twitter)) {
                throw new BadRequestException("Twitter must start with @");
            }
        }
   
        Users newUser = setNewUser(data);
        UsersRepository.persist(newUser);
    }
}
