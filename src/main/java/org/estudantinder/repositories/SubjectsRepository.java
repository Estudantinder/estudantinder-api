package org.estudantinder.repositories;

import javax.enterprise.context.ApplicationScoped;

import org.estudantinder.entities.Subject;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class SubjectsRepository implements PanacheRepository<Subject> {
    
    public Subject findByName(String name){
        return find("name", name).firstResult();
    }

}
