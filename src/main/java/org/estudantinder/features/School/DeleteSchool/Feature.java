package org.estudantinder.features.School.DeleteSchool;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.estudantinder.entities.School;
import org.estudantinder.repositories.SchoolsRepository;

@ApplicationScoped
public class Feature {
    
    @Inject
    SchoolsRepository schoolsRepository;

    public void execute(Long id) {
        School school = schoolsRepository.findById(id);
        
        if(school == null) {
            throw new EntityNotFoundException("School not found");
        }

        schoolsRepository.delete(school);
    }
}
