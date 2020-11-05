package org.estudantinder.features.School.UpdateSchool;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.estudantinder.entities.School;
import org.estudantinder.repositories.CoursesRepository;
import org.estudantinder.repositories.SchoolsRepository;

@ApplicationScoped
public class Feature {

    @Inject
    SchoolsRepository schoolsRepository;
    
    @Inject
    CoursesRepository coursesRepository;

    public void execute(Long id, DTO data)  {
        School updatedSchool = schoolsRepository.findById(id);

        if(updatedSchool == null) {
            throw new EntityNotFoundException("Couldn't find School");
        }


        if ( data.name != null ) {
            updatedSchool.setName(data.name);
        }
        if( data.address != null ) {
            updatedSchool.setAddress(data.address);
        }
        if(data.courses != null) {
            coursesRepository.deleteCourses(updatedSchool.getCourses());

            updatedSchool.setCourses(data.courses);
        }

        schoolsRepository.persist(updatedSchool);
    }

}