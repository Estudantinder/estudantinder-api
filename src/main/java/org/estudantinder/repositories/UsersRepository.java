package org.estudantinder.repositories;

import javax.enterprise.context.ApplicationScoped;

import org.estudantinder.entities.Users;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UsersRepository implements PanacheRepository<Users> {
    
    public Users findByEmail(String email){
        return find("email", email).firstResult();
    }

    public boolean isEmailAlreadyInUse(String email){
        Users studentWithEmailInUse = find("email", email).firstResult();
        if (studentWithEmailInUse != null) {
            return true;
        }

        return false;
    }

}
