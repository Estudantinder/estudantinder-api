package org.estudantinder.repositories;

import java.util.List;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;


import org.estudantinder.entities.Subject;
import org.estudantinder.entities.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UsersRepository implements PanacheRepository<User> {
    
    public User findByEmail(String email){
        return find("email", email).firstResult();
    }

    public Stream<User> findBySubject(Subject subject){

        //for some reason streamAll() doesn't work
        List<User> listedUsers = listAll();

        Stream<User> streamedUsers = listedUsers.stream();

        return streamedUsers.filter(user -> user.getSubjects().contains(subject));
    }

    public boolean isEmailAlreadyInUse(String email){
        User studentWithEmailInUse = find("email", email).firstResult();
        if (studentWithEmailInUse != null) {
            return true;
        }

        return false;
    }

}
