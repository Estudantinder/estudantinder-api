package org.estudantinder.features.School.ShowSchool;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.estudantinder.entities.School;
import org.estudantinder.repositories.SchoolsRepository;

@ApplicationScoped
public class Feature {
    
    @Inject
    SchoolsRepository schoolsRepository;

    public School execute(Long id) throws Exception {
        School school = schoolsRepository.findById(id);
        
        if(school == null) {
            throw new EntityNotFoundException("School id doesn't exists");
        }

        return school;
    }
}