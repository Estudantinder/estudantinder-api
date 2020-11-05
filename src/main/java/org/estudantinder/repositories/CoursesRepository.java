package org.estudantinder.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.estudantinder.entities.Course;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class CoursesRepository implements PanacheRepository<Course> {

	public void deleteCourses(List<Course> courses) {
        courses.forEach(course -> this.delete(course));
	}
    
}
