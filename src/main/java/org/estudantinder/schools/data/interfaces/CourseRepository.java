package org.estudantinder.schools.data.interfaces;

import java.util.UUID;

import org.estudantinder.schools.domain.models.Course;

public interface CourseRepository {
    Course findById(UUID id);
}
