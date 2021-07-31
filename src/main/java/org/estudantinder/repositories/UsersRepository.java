package org.estudantinder.repositories;

import java.util.List;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;


import org.estudantinder.entities.Subject;
import org.estudantinder.entities.Users;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UsersRepository implements PanacheRepository<Users> {
    
    public Users findByEmail(String email){
        return find("email", email).firstResult();
    }

    public Stream<Users> findBySubject(Subject subject){

        //for some reason streamAll() doesn't work
        List<Users> listedUsers = listAll();

        Stream<Users> streamedUsers = listedUsers.stream();

        return streamedUsers.filter(user -> user.getSubjects().contains(subject));
    }

    public boolean isEmailAlreadyInUse(String email){
        Users studentWithEmailInUse = find("email", email).firstResult();
        if (studentWithEmailInUse != null) {
            return true;
        }

        return false;
    }

}
