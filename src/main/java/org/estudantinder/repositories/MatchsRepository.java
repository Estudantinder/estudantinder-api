package org.estudantinder.repositories;

import javax.enterprise.context.ApplicationScoped;

import org.estudantinder.entities.Like;
import org.estudantinder.entities.Match;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MatchsRepository implements PanacheRepository<Match> {
    public Match findByLike(Like like) {
        return find("like", like).firstResult();
    }

    public Match findByMutualLike(Like like) {
        return find("mutualLike", like).firstResult();
    }
}
