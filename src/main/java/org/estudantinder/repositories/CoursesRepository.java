package org.estudantinder.repositories;

import javax.enterprise.context.ApplicationScoped;

import org.estudantinder.entities.Course;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CoursesRepository implements PanacheRepository<Course> {
    
}
