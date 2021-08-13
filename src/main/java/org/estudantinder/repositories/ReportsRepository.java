package org.estudantinder.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.estudantinder.entities.Report;
import org.estudantinder.entities.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ReportsRepository implements PanacheRepository<Report> {
    
    // find report by user
    public List<Report> findByUser(User user) {
        return find("reportedUser", user).list();
    }   
}
