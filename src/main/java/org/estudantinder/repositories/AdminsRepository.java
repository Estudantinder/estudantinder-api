package org.estudantinder.repositories;

import javax.enterprise.context.ApplicationScoped;

import org.estudantinder.entities.Admin;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class AdminsRepository implements PanacheRepository<Admin> {

    public Admin findByEmail(String email) {
        return find("email", email).firstResult();
    }

}
