package org.estudantinder.repositories;

import javax.enterprise.context.ApplicationScoped;

import org.estudantinder.entities.Dislike;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class DislikesRepository implements PanacheRepository<Dislike> {
    
}
