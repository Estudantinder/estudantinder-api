package org.estudantinder.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.estudantinder.entities.Dislike;
import org.estudantinder.entities.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class DislikesRepository implements PanacheRepository<Dislike> {
    public List<Dislike> findBySender(User user) {
        return find("sender", user).list();
    }

    public List<Dislike> findByReceiver(User user) {
        return find("receiver", user).list();
    }
}
