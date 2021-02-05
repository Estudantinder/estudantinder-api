package org.estudantinder.features.Users.EditUserFilters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Course;
import org.estudantinder.entities.Preferences;
import org.estudantinder.entities.School;
import org.estudantinder.entities.Subject;
import org.estudantinder.entities.Users;
import org.estudantinder.repositories.CoursesRepository;
import org.estudantinder.repositories.PreferencesRepository;
import org.estudantinder.repositories.SchoolsRepository;
import org.estudantinder.repositories.SubjectsRepository;
import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    UsersRepository usersRepository;

    @Inject
    CoursesRepository coursesRepository;

    @Inject
    SchoolsRepository schoolsRepository;

    @Inject
    SubjectsRepository subjectsRepository;

    @Inject
    PreferencesRepository preferencesRepository;

    private Course returnCourseIfExists(Long courseId) {
        Course course = coursesRepository.findById(courseId);

        if(course == null) {
            throw new EntityNotFoundException("Course Not Found");
        }

        return course;
    }

    private School returnSchoolIfExists(Long schoolId) {
        School school = schoolsRepository.findById(schoolId);

        if(school == null) {
            throw new EntityNotFoundException("Course Not Found");
        }

        return school;
    }
    
    private void treatStudentInvalidID(Users user) {
        if(user == null) {
            throw new NotFoundException("Student ID not valid");
        }
    } 

    private void treatSubjectsDuplicatedID(List<Long> subjects_ids) {
        if(isListFieldDuplicate(subjects_ids)) {
            throw new BadRequestException("Subject ID's can't be duplicated");
        }
    }
    public boolean isListFieldDuplicate(List<Long> subjects_id) {
        final Set<Long> uniqueIds = new HashSet<>();
        
        for (Long subject_id : subjects_id){
            if (!uniqueIds.add(subject_id)){
                return true;
            }
        }
        
        return false;
    }

    public Subject returnSubjectIfExists(Long subject_id) {
        Subject subject = subjectsRepository.findById(subject_id);

        if(subject == null) {
            throw new EntityNotFoundException("Subject id "+subject_id+" Not Found");
        }

        return subject;
    }

    public List<Subject> returnListOfSubjects(List<Long> subjects_ids) {
        List<Subject> subjects = new ArrayList<Subject>();
        
        treatSubjectsDuplicatedID(subjects_ids);
        subjects_ids.forEach(subject_id -> {
            Subject subject = returnSubjectIfExists(subject_id);
            subjects.add(subject);
        });

        return subjects;
    } 

    private Preferences mapToPreferences(Preferences userPreferences, DTO preferences) {
        if(preferences.gender != null ) {
            userPreferences.setGender(preferences.gender);
        }
        if(preferences.shift != 0) {
            userPreferences.setShift(preferences.shift);
        }
        if(preferences.school_year != 0) {
            userPreferences.setSchool_year(preferences.school_year);
        }
        if (preferences.course_id != null) {
            userPreferences.setCourse(returnCourseIfExists(preferences.course_id));
        }
        if (preferences.school_id != null) {
            userPreferences.setSchool(returnSchoolIfExists(preferences.school_id));
        }
        if( preferences.subjects_ids != null && 
            preferences.subjects_ids.size() > 0) {
            userPreferences.setSubjects(returnListOfSubjects(preferences.subjects_ids));
        }
        return userPreferences;
    }

    public Preferences execute(JsonWebToken jwt, DTO data) throws Exception {
        Long userId = Long.parseLong(jwt.getClaim("id").toString());
        Users authenticatedUser = usersRepository.findById(userId);

        treatStudentInvalidID(authenticatedUser);

        if(authenticatedUser.getPreferences() == null) {
            Preferences userPreferences = new Preferences();
            
            Preferences updatedUserPreferences = mapToPreferences(userPreferences, data);

            authenticatedUser.setPreferences(updatedUserPreferences);

            usersRepository.persist(authenticatedUser);
            return authenticatedUser.getPreferences();
        }

        Preferences userPreferences = authenticatedUser.getPreferences();
        Preferences updatedUserPreferences = mapToPreferences( userPreferences, data);

        preferencesRepository.persist(updatedUserPreferences);

        return authenticatedUser.getPreferences();
    }
}
