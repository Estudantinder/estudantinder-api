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
import javax.ws.rs.NotFoundException;
import javax.ws.rs.BadRequestException;

import org.estudantinder.entities.Contacts;
import org.estudantinder.entities.Course;
import org.estudantinder.entities.User;
import org.estudantinder.features.Users.CreateUser.DTO.ContactsDTO;
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

    public Course returnCourseIfExists(Long courseId) {
        Course course = coursesRepository.findById(courseId);

        if(course == null) {
            throw new NotFoundException("Curso Não encontrado");
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
            throw new NotFoundException("Materia com o id "+subject_id+" não encontrada");
        }

        return subject;
    }

    public List<Subject> returnListOfSubjects(List<Long> subjects_id) {
        List<Subject> subjects = new ArrayList<Subject>();
        
        if(isListFieldDuplicate(subjects_id)) {
            throw new BadRequestException("Id da materia não pode ser duplicado");
        }

        subjects_id.forEach(subject_id -> {
            Subject subject = returnSubjectIfExists(subject_id);
            subjects.add(subject);
        });

        return subjects;
    } 

    public Contacts setNewStudenContacts(ContactsDTO contacts) {
        Contacts newUserContacts = new Contacts();

        newUserContacts.setWhatsapp(contacts.whatsapp);
        newUserContacts.setFacebook(contacts.facebook);
        newUserContacts.setInstagram(contacts.instagram);
        newUserContacts.setTwitter(contacts.twitter);

        return newUserContacts;
    }

    public User setNewUser(UserDTO User) {
        User newUser = new User();

        newUser.setName(User.name);
        newUser.setEmail(User.email);
        newUser.setPassword(User.password.trim());
        newUser.setSchool_year(User.school_year);
        newUser.setBirth_date(User.birth_date);
        newUser.setBio(User.bio);
        newUser.setGender(User.gender);
        newUser.setShift(User.shift);
        newUser.setClassroom(Character.toUpperCase(User.classroom));
        newUser.setSubjects(returnListOfSubjects(User.subjects_ids));
        newUser.setCourse(returnCourseIfExists(User.course_id));
        newUser.setContacts(setNewStudenContacts(User.contacts));
        
         return newUser;
    }

    public void execute(UserDTO data) throws Exception {
        boolean isEmailAlreadyInUse = UsersRepository.isEmailAlreadyInUse(data.email);

        if(isEmailAlreadyInUse) {
            throw new EntityExistsException("Email já está em uso");
        }
   
        if(data.contacts == null) {
            throw new NotFoundException("Ao menos 1 contato é necessario");
        }

        if(checkIfPasswordDoesntContainsNumber(data.password)) {
            throw new BadRequestException("A senha deve conter ao menos 1 numero");
        }

        if(checkIfAgeIsntCorrect(data.birth_date)) {
            throw new BadRequestException("A idade minima é 14 e maxima é 21");
        }

        if(checkIfClassroomIsAlphabetical(data.classroom)) {
            throw new BadRequestException("Classe tem que ser uma letra do alfabeto");
        }
        
        User newUser = setNewUser(data);
        UsersRepository.persist(newUser);
    }
}
