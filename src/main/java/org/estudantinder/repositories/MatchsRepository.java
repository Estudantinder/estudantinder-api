package org.estudantinder.repositories;

import javax.enterprise.context.ApplicationScoped;

import org.estudantinder.entities.Match;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MatchsRepository implements PanacheRepository<Match> {
    
}
