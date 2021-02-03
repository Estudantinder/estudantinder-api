package org.estudantinder.features.Users.EditUserFilters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Course;
import org.estudantinder.entities.Preferences;
import org.estudantinder.entities.Users;
import org.estudantinder.repositories.CoursesRepository;
import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    UsersRepository usersRepository;

    @Inject
    CoursesRepository coursesRepository;

    private Course returnCourseIfExists(Long courseId) {
        Course course = coursesRepository.findById(courseId);

        if(course == null) {
            throw new EntityNotFoundException("Course Not Found");
        }

        return course;
    }
    
    private void treatInvalidID(Users user) {
        if(user == null) {
            throw new NotFoundException("Student ID not valid");
        }
    } 

    private Preferences mapToPreferences(DTO preferences) {
        Preferences newUserPreferences = new Preferences();
        
        newUserPreferences.setGender(preferences.gender);
        newUserPreferences.setShift(preferences.shift);
        newUserPreferences.setSchool_year(preferences.school_year);
        if (preferences.course_id != null) {
            newUserPreferences.setCourse(returnCourseIfExists(preferences.course_id));
        }

        return newUserPreferences;
    }

    public Preferences execute(JsonWebToken jwt, DTO data) throws Exception {
        Long userId = Long.parseLong(jwt.getClaim("id").toString());
        Users authenticatedUser = usersRepository.findById(userId);

        treatInvalidID(authenticatedUser);

        authenticatedUser.setPreferences(mapToPreferences(data));

        return authenticatedUser.getPreferences();
    }
}
