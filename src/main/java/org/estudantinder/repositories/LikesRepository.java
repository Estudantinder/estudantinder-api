package org.estudantinder.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.estudantinder.entities.Like;
import org.estudantinder.entities.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class LikesRepository implements PanacheRepository<Like> {
    public List<Like> findBySender(User user) {
        return find("sender", user).list();
    }

    public List<Like> findByReceiver(User user) {
        return find("receiver", user).list();
    }
}
