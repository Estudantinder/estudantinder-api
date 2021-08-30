package org.estudantinder.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.estudantinder.entities.Report;
import org.estudantinder.entities.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class ReportsRepository implements PanacheRepository<Report> {
    
    // find report by user
    public List<Report> findByUser(User user) {
        return find("reportedUser", user).list();
    }   

    // find report by type
    public List<Report> findByType(String type) {
        return find("type", type).list();
    }   

    // find report by user and type
    public List<Report> findByUserAndType(User user, String type) {
        return find("reportedUser = :reportedUser and type = :type", 
            Parameters.with("reportedUser", user)
            .and("type", type)).list();
    }

}
