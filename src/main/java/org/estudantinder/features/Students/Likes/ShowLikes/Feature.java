package org.estudantinder.features.Students.Likes.ShowLikes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Likes;
import org.estudantinder.entities.Users;
import org.estudantinder.features.commom.Student;
import org.estudantinder.repositories.LikesRepository;
import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    LikesRepository likesRepository;

    @Inject
    UsersRepository usersRepository;

    void throwExceptionIfUserNotValid(Users user) {
        if (user == null) {
            throw new NotFoundException("User id not found");
        }
    }

    List<Student> listStudentLikes(Users authenticatedUser) {
        Stream<Likes> userLikes = likesRepository.stream("sender", authenticatedUser);

        List<Student> studentLikes = userLikes.map(studentLike -> Student.mapUserToStudent(studentLike.getReceiver()))
                .collect(Collectors.toList());

        return studentLikes;
    }

    public List<Student> execute(JsonWebToken jwt) throws Exception {
        Long userId = Long.parseLong(jwt.getClaim("id").toString());
        Users authenticatedUser = usersRepository.findById(userId);

        throwExceptionIfUserNotValid(authenticatedUser);

        return listStudentLikes(authenticatedUser);
    }

}