package org.estudantinder.schools.infra.panache.repositories;

import java.util.UUID;

import org.estudantinder.schools.data.interfaces.CourseRepository;
import org.estudantinder.schools.domain.models.Course;
import org.estudantinder.schools.infra.panache.entities.PanacheCourse;

public class PanacheCourseRepository implements CourseRepository {

    @Override
    public Course findById(UUID id) {
        PanacheCourse panacheCourse = PanacheCourse.findById(id);

        if(panacheCourse == null) return null;

        return panacheCourse.toCourse();
    } 

}
