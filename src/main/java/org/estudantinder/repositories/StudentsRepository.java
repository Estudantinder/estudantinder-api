package org.estudantinder.repositories;

import javax.enterprise.context.ApplicationScoped;

import org.estudantinder.entities.Student;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class StudentsRepository implements PanacheRepository<Student> {
    
    public boolean isEmailAlreadyInUse(String email){
        Student studentWithEmailInUse = find("email", email).firstResult();
        if (studentWithEmailInUse != null) {
            return true;
        }

        return false;
    }

}
