package org.estudantinder.features.School.CreateSchool;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;

import org.estudantinder.entities.Course;
import org.estudantinder.entities.School;
import org.estudantinder.features.School.CreateSchool.DTO.CourseDTO;
import org.estudantinder.features.School.CreateSchool.DTO.SchoolDTO;
import org.estudantinder.repositories.SchoolsRepository;

@ApplicationScoped
public class Feature {

    @Inject
    SchoolsRepository schoolsRepository;

    Course mapCourseDtoToCourse(CourseDTO course, School courseSchool) {
        Course newCourse = new Course();
        newCourse.setName(course.name);
        newCourse.setSchool(courseSchool);

        return newCourse;
    } 

    public void execute(SchoolDTO data) throws Exception {
        School userAlreadyExists = schoolsRepository.findByName(data.name);

        if (userAlreadyExists != null) {
            throw new EntityExistsException("School already exists");
        }
        
        School newSchool = new School();
        
        newSchool.setName(data.name);
        newSchool.setAddress(data.address);

        List<Course> courses = data.courses.stream().map(courseDto -> mapCourseDtoToCourse(courseDto, newSchool)).collect(Collectors.toList());
        newSchool.setCourses(courses);

        schoolsRepository.persist(newSchool);
    }
}
