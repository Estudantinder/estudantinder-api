package org.estudantinder.features.Users.Likes.ShowLikes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Likes;
import org.estudantinder.entities.Student;
import org.estudantinder.features.Users.common.User;
import org.estudantinder.repositories.LikesRepository;
import org.estudantinder.repositories.StudentsRepository;

@ApplicationScoped
public class Feature {

    @Inject
    LikesRepository likesRepository;
    
    @Inject
    StudentsRepository studentsRepository;

    List<User> listUserLikes(Student authenticatedStudent) {
        Stream<Likes> studentLikes = likesRepository.stream("sender", authenticatedStudent);
        
        List<User> userLikes = studentLikes.map(studentLike -> 
            User.mapStudentToUser(studentLike.getReceiver())).collect(Collectors.toList());

        return userLikes;
    }

    void throwErrorIfStudentNotValid(Student student) {
        if(student == null) {
            throw new NotFoundException("User id not found");
        }
    }

    public List<User> execute(JsonWebToken jwt) throws Exception {
        Long userId = Long.parseLong(jwt.getClaim("id").toString());
        Student authenticatedStudent = studentsRepository.findById(userId);

        throwErrorIfStudentNotValid(authenticatedStudent);

        return listUserLikes(authenticatedStudent);
    }
    
}