package org.estudantinder.features.Student.CreateStudent;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

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
        newStudentPreferences.setSchoolShift(preferences.schoolShift);
        newStudentPreferences.setSchoolYear(preferences.schoolYear);
        newStudentPreferences.setCourse(returnCourseIfExists(preferences.courseId));

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
        newStudent.setSchoolYear(student.schoolYear);
        newStudent.setBirthday(student.birthday);
        newStudent.setBiography(student.biography);
        newStudent.setGender(student.gender);
        newStudent.setSchoolShift(student.schoolShift);
        newStudent.setPhotos(student.photos);
        newStudent.setFavoriteSubjects(student.favoriteSubjects);
        newStudent.setCourse(returnCourseIfExists(student.courseId));
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
        
        Student newStudent = setNewStudent(data);
        
        studentsRepository.persist(newStudent);
    }
}
