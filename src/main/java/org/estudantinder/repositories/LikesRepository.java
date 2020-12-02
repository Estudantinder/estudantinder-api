package org.estudantinder.repositories;

import javax.enterprise.context.ApplicationScoped;

import org.estudantinder.entities.Likes;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class LikesRepository implements PanacheRepository<Likes> {
    
}
