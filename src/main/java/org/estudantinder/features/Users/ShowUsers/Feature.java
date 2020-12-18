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
import org.estudantinder.repositories.LikesRepository;
import org.estudantinder.repositories.StudentsRepository;

@ApplicationScoped
public class Feature {

    @Inject
    StudentsRepository studentsRepository;

    @Inject
    LikesRepository likesRepository;

    void throwExceptionIfStudentNotValid(Student authenticatedStudent) {
        if(authenticatedStudent == null) {
            throw new NotFoundException("User id not found");
        }
    }

    Stream<Student> removesAuthenticatedStudentFromStream(Student authenticatedStudent, Stream<Student> allStudents) {
        return allStudents.filter( student -> 
            student.getId() != authenticatedStudent.getId());
    }

    Stream<Student> filterStudentsByPreferences(Preferences preferences, Stream<Student> allStudents) {
        if(preferences != null ) {
            if( preferences.getGender() != null ) {
                if(preferences.getGender().length() != 0) {
                    allStudents = allStudents.filter( student -> 
                        student.getGender().equals(preferences.getGender()));
                }
            }
    
            if(preferences.getShift() != 0) {
                allStudents = allStudents.filter( student -> 
                student.getShift() == preferences.getShift());
            }
    
            if(preferences.getSchool_year() != 0 ) {
                allStudents = allStudents.filter( student -> 
                    student.getSchool_year() == preferences.getSchool_year() );
            }
    
            if(preferences.getCourse() != null ) {
                allStudents = allStudents.filter( student -> 
                    student.getCourse() == preferences.getCourse() );
            }
    
            return allStudents;
        }
        
        return allStudents;
    }

    Stream<Student> filterStudentsAlreadyLiked( Student student, Stream<Student> allStudents) {
        List<Student> studentsAlreadyLiked = likesRepository
            .stream("sender", student)
            .map(like -> like.getReceiver())
            .collect(Collectors.toList());
        
        Stream<Student> studentsNotLiked = allStudents.filter(s -> 
            !studentsAlreadyLiked.contains(s));

        return studentsNotLiked;
    }

    Stream<Student> filterStudents(Student student, Stream<Student> allStudents) {
        Stream<Student> filteredStudents = filterStudentsByPreferences(student.getPreferences(), allStudents);

        filteredStudents = removesAuthenticatedStudentFromStream(student, filteredStudents);

        filteredStudents = filterStudentsAlreadyLiked(student, filteredStudents);

        return filteredStudents;
    }
    
    List<User> listFilteredUsers(Student student, Stream<Student> allStudents) {
        Stream<Student> filteredStudents = filterStudents(student, allStudents);

        Stream<User> filteredUsers =  filteredStudents.map(t -> User.mapStudentToUser(t));

        return filteredUsers.collect(Collectors.toList());
    }


    public List<User> execute(JsonWebToken jwt) throws Exception {
        long userId = Long.parseLong(jwt.getClaim("id").toString());
        Student authenticatedStudent = studentsRepository.findById(userId);

        throwExceptionIfStudentNotValid(authenticatedStudent);

        Stream<Student> allStudents = studentsRepository.streamAll();
        return listFilteredUsers(authenticatedStudent, allStudents);
    }
}