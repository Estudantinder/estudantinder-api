package org.estudantinder.features.Students.Likes.ShowLikes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Like;
import org.estudantinder.entities.User;
import org.estudantinder.features.commom.Student;
import org.estudantinder.repositories.LikesRepository;
import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    LikesRepository likesRepository;

    @Inject
    UsersRepository usersRepository;

    void throwExceptionIfUserNotValid(User user) {
        if (user == null) {
            throw new NotFoundException("User id not found");
        }
    }

    List<Student> listStudentLikes(User authenticatedUser) {
        Stream<Like> userLikes = likesRepository.stream("sender", authenticatedUser);

        List<Student> studentLikes = userLikes.map(studentLike -> Student.mapUserToStudent(studentLike.getReceiver()))
                .collect(Collectors.toList());

        return studentLikes;
    }

    public List<Student> execute(JsonWebToken jwt) throws Exception {
        Long userId = Long.parseLong(jwt.getClaim("id").toString());
        User authenticatedUser = usersRepository.findById(userId);

        throwExceptionIfUserNotValid(authenticatedUser);

        return listStudentLikes(authenticatedUser);
    }

}