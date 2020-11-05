package org.estudantinder.repositories;

import javax.enterprise.context.ApplicationScoped;

import org.estudantinder.entities.School;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class SchoolsRepository implements PanacheRepository<School>{
    
    public School findByName(String name){
        return find("name", name).firstResult();
    }

}
