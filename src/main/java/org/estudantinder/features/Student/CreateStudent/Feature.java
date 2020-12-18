package org.estudantinder.features.Student.CreateStudent;

import java.time.LocalDate;
import java.time.Period;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.BadRequestException;

import org.estudantinder.entities.Contacts;
import org.estudantinder.entities.Course;
import org.estudantinder.entities.Preferences;
import org.estudantinder.entities.Student;
import org.estudantinder.features.Student.CreateStudent.DTO.ContactsDTO;
import org.estudantinder.features.Student.CreateStudent.DTO.PreferencesDTO;
import org.estudantinder.features.Student.CreateStudent.DTO.StudentDTO;
import org.estudantinder.repositories.CoursesRepository;
import org.estudantinder.repositories.StudentsRepository;

@ApplicationScoped
public class Feature {

    @Inject
    StudentsRepository studentsRepository;
    
    @Inject
    CoursesRepository coursesRepository;

    public boolean checkIfAgeIsntCorrect(LocalDate birth_date) {
        LocalDate today = LocalDate.now();
        int studentAge = Period.between(birth_date, today).getYears();

        if(studentAge < 14 || studentAge > 21) {
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
            throw new EntityNotFoundException("Course Not Found");
        }

        return course;
    } 

    public Preferences setNewStudentPreferences(PreferencesDTO preferences) {
        Preferences newStudentPreferences = new Preferences();
        
        newStudentPreferences.setGender(preferences.gender);
        newStudentPreferences.setShift(preferences.shift);
        newStudentPreferences.setSchool_year(preferences.school_year);
        if (preferences.course_id != null) {
            newStudentPreferences.setCourse(returnCourseIfExists(preferences.course_id));
        }

        return newStudentPreferences;
    }

    public Contacts setNewStudenContacts(ContactsDTO contacts) {
        Contacts newStudentContacts = new Contacts();

        newStudentContacts.setWhatsapp(contacts.whatsapp);
        newStudentContacts.setFacebook(contacts.facebook);
        newStudentContacts.setInstagram(contacts.instagram);
        newStudentContacts.setTwitter(contacts.twitter);

        return newStudentContacts;
    }

    public Student setNewStudent(StudentDTO student) {
        Student newStudent = new Student();

        newStudent.setName(student.name);
        newStudent.setEmail(student.email);
        newStudent.setPassword(student.password);
        newStudent.setSchool_year(student.school_year);
        newStudent.setBirth_date(student.birth_date);
        newStudent.setBio(student.bio);
        newStudent.setGender(student.gender);
        newStudent.setShift(student.shift);
        newStudent.setClassroom(Character.toUpperCase(student.classroom));
        newStudent.setPhotos(student.photos);
        newStudent.setSubjects(student.subjects);
        newStudent.setCourse(returnCourseIfExists(student.course_id));
        newStudent.setContacts(setNewStudenContacts(student.contacts));
        
        if(student.preferences != null) {
            newStudent.setPreferences(setNewStudentPreferences(student.preferences));
        }

        return newStudent;
    }

    public void execute(StudentDTO data) throws Exception {
        boolean isEmailAlreadyInUse = studentsRepository.isEmailAlreadyInUse(data.email);

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
            throw new BadRequestException("Student age must be between 14-21");
        }

        if(checkIfClassroomIsAlphabetical(data.classroom)) {
            throw new BadRequestException("Classroom must be alphabetical");
        }
        
        Student newStudent = setNewStudent(data);
        
        studentsRepository.persist(newStudent);
    }
}
