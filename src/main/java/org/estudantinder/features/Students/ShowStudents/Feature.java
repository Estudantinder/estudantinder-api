package org.estudantinder.features.Students.ShowStudents;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Preferences;
import org.estudantinder.entities.Subject;
import org.estudantinder.entities.Users;
import org.estudantinder.features.Students.common.Student;
import org.estudantinder.repositories.LikesRepository;
import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    UsersRepository usersRepository;

    @Inject
    LikesRepository likesRepository;

    void throwExceptionIfUserNotValid(Users authenticatedUser) {
        if(authenticatedUser == null) {
            throw new NotFoundException("User id not found");
        }
    }

    Stream<Users> removesAuthenticatedUserFromStream(Users authenticatedUser, Stream<Users> allUsers) {
        return allUsers.filter( user -> 
            user.getId() != authenticatedUser.getId());
    }

    Stream<Users> filterUsersByPreferences(Preferences preferences, Stream<Users> allUsers) {

        if(preferences != null ) {
            if( preferences.getGender() != null ) {
                if(preferences.getGender().length() != 0) {
                    allUsers = allUsers.filter( user -> 
                        user.getGender().equals(preferences.getGender()));
                }
            }
    
            if(preferences.getShift() != 0) {
                allUsers = allUsers.filter( user -> 
                user.getShift() == preferences.getShift());
            }
    
            if(preferences.getSchool_year() != 0 ) {
                allUsers = allUsers.filter( user -> 
                    user.getSchool_year() == preferences.getSchool_year() );
            }
    
            if(preferences.getCourse() != null ) {
                allUsers = allUsers.filter( user -> 
                    user.getCourse() == preferences.getCourse() );
            }

            if(preferences.getSubjects() != null ) {
                if(preferences.getSubjects().size() > 0) {
                    allUsers = allUsers.filter( user -> {
                        for(Subject subject : user.getSubjects()) {
                            if(preferences.getSubjects().contains(subject)) {
                                return true;
                            }
                        }
                        return false;
                    });
                }
            }
    
            return allUsers;
        }
        
        return allUsers;
    }

    Stream<Users> filterUsersAlreadyLiked( Users User, Stream<Users> allUsers) {
        List<Users> usersAlreadyLiked = likesRepository
            .stream("sender", User)
            .map(like -> like.getReceiver())
            .collect(Collectors.toList());
        
        Stream<Users> usersNotLiked = allUsers.filter(s -> 
            !usersAlreadyLiked.contains(s));

        return usersNotLiked;
    }

    Stream<Users> filteredUsers(Users user, Stream<Users> allUsers) {
        Stream<Users> filteredUsers = filterUsersByPreferences(user.getPreferences(), allUsers);

        filteredUsers = removesAuthenticatedUserFromStream(user, filteredUsers);

        filteredUsers = filterUsersAlreadyLiked(user, filteredUsers);

        return filteredUsers;
    }
    
    List<Student> listFilteredUsers(Users user, Stream<Users> allUsers) {
        Stream<Users> filteredUsers = filteredUsers(user, allUsers);

        Stream<Student> filteredStudents =  filteredUsers.map(s -> Student.mapUserToStudent(s));

        return filteredStudents.collect(Collectors.toList());
    }


    public List<Student> execute(JsonWebToken jwt) throws Exception {
        long userId = Long.parseLong(jwt.getClaim("id").toString());
        Users authenticatedUser = usersRepository.findById(userId);

        throwExceptionIfUserNotValid(authenticatedUser);

        Stream<Users> allUsers = usersRepository.streamAll();
        return listFilteredUsers(authenticatedUser, allUsers);
    }
}