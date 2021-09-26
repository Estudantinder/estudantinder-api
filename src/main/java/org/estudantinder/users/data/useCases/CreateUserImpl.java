package org.estudantinder.users.data.useCases;

import java.util.UUID;

import javax.ws.rs.WebApplicationException;

import org.estudantinder.schools.data.interfaces.CourseRepository;
import org.estudantinder.schools.data.interfaces.SchoolRepository;
import org.estudantinder.schools.domain.models.Course;
import org.estudantinder.schools.domain.models.School;
import org.estudantinder.users.data.interfaces.UserRepository;
import org.estudantinder.users.domain.dtos.CreateUserDTO;
import org.estudantinder.users.domain.models.User;
import org.estudantinder.users.domain.useCases.CreateUser;

public class CreateUserImpl implements CreateUser {

    private UserRepository userRepository;
    private SchoolRepository schoolRepository;
    private CourseRepository courseRepository;

    public CreateUserImpl(
            UserRepository userRepository,
            SchoolRepository schoolRepository,
            CourseRepository courseRepository
        ) {

        this.userRepository = userRepository;
        this.schoolRepository = schoolRepository;
        this.courseRepository = courseRepository;
    }

    private void checkCourseExistence(UUID id, String errorMessage) {
        Course course = courseRepository.findById(id);
        if(course == null)
            throw new WebApplicationException(errorMessage, 404);
    }

    private void checkSchoolExistence(UUID id, String errorMessage) {
        School school = schoolRepository.findById(id);
        if(school == null)
            throw new WebApplicationException(errorMessage, 404);
    }

    public void validatePreferencesEntitiesExist(CreateUserDTO createUserDTO) {

        if(createUserDTO.preferences.school_id != null) 
            checkSchoolExistence(createUserDTO.preferences.school_id,
                "Escola inserida nas preferencias não encontrada");

        if(createUserDTO.preferences.course_id != null) 
            checkCourseExistence(createUserDTO.preferences.course_id,
                "Curso inserido nas preferencias não encontrado");
        
    }

    @Override
    public User create(CreateUserDTO createUserDTO) {
        User user = userRepository.findByEmail(createUserDTO.email);

        if(user != null)
            throw new WebApplicationException("Email já está em uso", 409);
            
        checkCourseExistence(createUserDTO.course_id, "Curso do usuário não encontrado");
        
        if (createUserDTO.preferences != null)
            validatePreferencesEntitiesExist(createUserDTO);

        return userRepository.create(createUserDTO);
    }

}
