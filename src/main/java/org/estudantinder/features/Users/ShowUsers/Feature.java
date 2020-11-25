package org.estudantinder.features.Users.ShowUsers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Preferences;
import org.estudantinder.entities.Student;
import org.estudantinder.features.Users.common.User;
import org.estudantinder.repositories.StudentsRepository;

@ApplicationScoped
public class Feature {

    @Inject
    StudentsRepository studentsRepository;

    Stream<Student> removesAuthenticatedStudentFromStream(Student authenticatedStudent, Stream<Student> allStudents) {
        return allStudents.filter( student -> 
            student.getId() != authenticatedStudent.getId());
    }

    Stream<Student> filterStudentsByPreferences(Preferences preferences, Stream<Student> allStudents) {
        if( preferences.getGender() != null ) {
            if(preferences.getGender().length() != 0) {
                allStudents = allStudents.filter( student -> 
                    student.getGender().equals(preferences.getGender()));
            }
        }

        if(preferences.getSchoolShift() != null) {
            if(preferences.getSchoolShift().length() != 0) {
                allStudents = allStudents.filter( student -> 
                student.getSchoolShift().equals(preferences.getSchoolShift()));
            }
        }

        if(preferences.getSchoolYear() != 0 ) {
            allStudents = allStudents.filter( student -> 
                student.getSchoolYear() == preferences.getSchoolYear() );
        }

        if(preferences.getCourse() != null ) {
            allStudents = allStudents.filter( student -> 
                student.getCourse() == preferences.getCourse() );
        }

        return allStudents;
    }

    Stream<Student> filterStudents(Student student) {
        Stream<Student> filteredStudents = studentsRepository.streamAll();

        if(student.getPreferences() != null) {
            filteredStudents = filterStudentsByPreferences(student.getPreferences(), filteredStudents);
        }

        filteredStudents = removesAuthenticatedStudentFromStream(student, filteredStudents);

        return filteredStudents;
    }
    
    List<User> listFilteredUsers(Student student) {
        Stream<Student> filteredStudents = filterStudents(student);

        Stream<User> filteredUsers =  filteredStudents.map(t -> User.mapStudentToUser(t));

        return filteredUsers.collect(Collectors.toList());
    }


    public List<User> execute(JsonWebToken jwt) throws Exception {
        long userId = Long.parseLong(jwt.getClaim("id").toString());
        Student authenticatedStudent = studentsRepository.findById(userId);

        if(authenticatedStudent == null) {
            throw new NotFoundException("User id not found");
        }

        return listFilteredUsers(authenticatedStudent);
    }
}