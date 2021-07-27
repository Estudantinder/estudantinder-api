package org.estudantinder.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.estudantinder.entities.Likes;
import org.estudantinder.entities.Users;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class LikesRepository implements PanacheRepository<Likes> {
    public List<Likes> findBySender(Users user) {
        return find("sender", user).list();
    }

    public List<Likes> findByReceiver(Users user) {
        return find("receiver", user).list();
    }
}
