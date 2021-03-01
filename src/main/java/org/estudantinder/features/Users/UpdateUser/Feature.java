package org.estudantinder.features.Users.UpdateUser;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Contacts;
import org.estudantinder.entities.Course;
import org.estudantinder.entities.Users;
import org.estudantinder.features.Users.UpdateUser.DTO.ContactsDTO;
import org.estudantinder.features.Users.UpdateUser.DTO.UserDTO;
import org.estudantinder.entities.Subject;

import org.estudantinder.repositories.CoursesRepository;
import org.estudantinder.repositories.UsersRepository;
import org.estudantinder.repositories.SubjectsRepository;

@ApplicationScoped
public class Feature {
    
    @Inject
    UsersRepository usersRepository;
    
    @Inject
    CoursesRepository coursesRepository;
    
    @Inject
    SubjectsRepository subjectsRepository;

    private void treatInvalidID(Users authenticatedUser) {
                if(authenticatedUser == null) 
            throw new NotFoundException("Token ID not found");
        
    }
    private void treatPasswordIfExists(String password) {
        if(password != null) {
            if(checkIfPasswordDoesntContainsNumber(password)) 
            throw new BadRequestException("Password must contain at least 1 number");
        }
    }
    private void treatBirthDateIfExists(LocalDate birth_date) {
        if(birth_date != null) {
            if(checkIfAgeIsntCorrect(birth_date)) 
            throw new BadRequestException("User age must be between 14-21");
        }
    }
    private void treatClassroomIfExists(char classroom) {
        if(Character.isLetterOrDigit(classroom)) {
            if(checkIfClassroomIsAlphabetical(classroom))
            throw new BadRequestException("Classroom must be alphabetical");
        }
    }
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
    
    public Contacts updateStudentContacts(ContactsDTO updatedContacts) {
        Contacts authenticatedUserContacts = new Contacts();
        
        if(updatedContacts.whatsapp != null) 
        authenticatedUserContacts.setWhatsapp(updatedContacts.whatsapp);
        
        if(updatedContacts.facebook != null) 
        authenticatedUserContacts.setFacebook(updatedContacts.facebook);
        
        if(updatedContacts.instagram != null) 
        authenticatedUserContacts.setInstagram(updatedContacts.instagram);
        
        if(updatedContacts.twitter != null) 
        authenticatedUserContacts.setTwitter(updatedContacts.twitter);
        
        return authenticatedUserContacts;
    }
    
    public Users updateUser(Users authenticatedUser, UserDTO updatedData) {
        
        if(updatedData.name != null) 
        authenticatedUser.setName(updatedData.name);
        
        if(updatedData.email != null) 
        authenticatedUser.setEmail(updatedData.email);
        
        if(updatedData.password != null) 
        authenticatedUser.setPassword(updatedData.password);
        
        if(updatedData.school_year != 0) 
        authenticatedUser.setSchool_year(updatedData.school_year);
        
        if(updatedData.birth_date != null) 
        authenticatedUser.setBirth_date(updatedData.birth_date);
        
        if(updatedData.bio != null) 
        authenticatedUser.setBio(updatedData.bio);
        
        if(updatedData.gender != null) 
        authenticatedUser.setGender(updatedData.gender);
        
        if(updatedData.shift != 0) 
        authenticatedUser.setShift(updatedData.shift);
        
        if(Character.isAlphabetic(updatedData.classroom)) 
        authenticatedUser.setClassroom(updatedData.classroom);
        
        if(updatedData.subjects_ids != null) 
        authenticatedUser.setSubjects(returnListOfSubjects(updatedData.subjects_ids));
        
        if(updatedData.course_id != null) 
        authenticatedUser.setCourse(returnCourseIfExists(updatedData.course_id));
        
        if(updatedData.contacts != null) 
        authenticatedUser.setContacts(updateStudentContacts(updatedData.contacts));
        
        return authenticatedUser;
    }
    
    public void execute(JsonWebToken jwt, UserDTO data) throws Exception {
        Long userId = Long.parseLong(jwt.getClaim("id").toString());
        Users authenticatedUser = usersRepository.findById(userId);
        
        treatInvalidID(authenticatedUser);
        treatBirthDateIfExists(data.birth_date);
        treatPasswordIfExists(data.password);
        treatClassroomIfExists(data.classroom);

        Users updatedUser = updateUser(authenticatedUser, data);
        usersRepository.persist(updatedUser);
    }
}
