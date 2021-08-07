package org.estudantinder.repositories;

import javax.enterprise.context.ApplicationScoped;

import org.estudantinder.entities.Report;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ReportsRepository implements PanacheRepository<Report> {
    
}
