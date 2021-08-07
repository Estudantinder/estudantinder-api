package org.estudantinder.features.Students.ShowStudents;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Preferences;
import org.estudantinder.entities.User;
import org.estudantinder.features.commom.Student;
import org.estudantinder.repositories.DislikesRepository;
import org.estudantinder.repositories.LikesRepository;
import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    UsersRepository usersRepository;

    @Inject
    LikesRepository likesRepository;

    @Inject
    DislikesRepository dislikesRepository;

    void throwExceptionIfUserNotValid(User authenticatedUser) {
        if (authenticatedUser == null) {
            throw new NotFoundException("User id not found");
        }
    }

    Stream<User> removesAuthenticatedUserFromStream(User authenticatedUser, Stream<User> allUsers) {
        return allUsers.filter(user -> user.getId() != authenticatedUser.getId());
    }

    Stream<User> filterUsersByPreferences(Preferences preferences, Stream<User> allUsers) {

        if (preferences != null) {
            if (preferences.getGender() != null) {
                if (preferences.getGender().length() != 0) {
                    allUsers = allUsers.filter(user -> user.getGender().equals(preferences.getGender()));
                }
            }

            if (preferences.getShift() != 0) {
                allUsers = allUsers.filter(user -> user.getShift() == preferences.getShift());
            }

            if (preferences.getSchool_year() != 0) {
                allUsers = allUsers.filter(user -> user.getSchool_year() == preferences.getSchool_year());
            }

            if (preferences.getSchool() != null) {
                allUsers = allUsers.filter(user -> user.getCourse().getSchool() == preferences.getSchool());
            }

            if (preferences.getCourse() != null) {
                allUsers = allUsers.filter(user -> user.getCourse() == preferences.getCourse());
            }

            if (preferences.getSubjects() != null) {
                if (preferences.getSubjects().size() > 0) {
                    allUsers = allUsers.filter(user -> preferences.getSubjects()
                            .stream().anyMatch(subject -> user.getSubjects().contains(subject)));
                }
            }

            return allUsers;
        }

        return allUsers;
    }

    Stream<User> filterUsersAlreadyLiked(User User, Stream<User> allUsers) {
        List<User> usersAlreadyLiked = likesRepository.stream("sender", User).map(like -> like.getReceiver())
                .collect(Collectors.toList());

        Stream<User> usersNotLiked = allUsers.filter(s -> !usersAlreadyLiked.contains(s));

        return usersNotLiked;
    }

    Stream<User> filterUsersAlreadyDisliked(User User, Stream<User> allUsers) {
        List<User> usersAlreadyDisliked = dislikesRepository.stream("sender", User)
                .map(dislike -> dislike.getReceiver()).collect(Collectors.toList());

        Stream<User> usersNotDisliked = allUsers.filter(s -> !usersAlreadyDisliked.contains(s));

        return usersNotDisliked;
    }

    Stream<User> filteredUsers(User user, Stream<User> allUsers) {
        Stream<User> filteredUsers = filterUsersByPreferences(user.getPreferences(), allUsers);

        filteredUsers = removesAuthenticatedUserFromStream(user, filteredUsers);

        filteredUsers = filterUsersAlreadyLiked(user, filteredUsers);
        filteredUsers = filterUsersAlreadyDisliked(user, filteredUsers);

        return filteredUsers;
    }

    List<Student> listFilteredUsers(User user, Stream<User> allUsers) {
        Stream<User> filteredUsers = filteredUsers(user, allUsers);

        Stream<Student> filteredStudents = filteredUsers.map(s -> Student.mapUserToStudent(s));

        return filteredStudents.collect(Collectors.toList());
    }

    public List<Student> execute(JsonWebToken jwt) throws Exception {
        long userId = Long.parseLong(jwt.getClaim("id").toString());
        User authenticatedUser = usersRepository.findById(userId);

        throwExceptionIfUserNotValid(authenticatedUser);

        List<User> allUsers = usersRepository.listAll();

        Stream<User> allUsersStream = allUsers.stream();

        return listFilteredUsers(authenticatedUser, allUsersStream);
    }
}