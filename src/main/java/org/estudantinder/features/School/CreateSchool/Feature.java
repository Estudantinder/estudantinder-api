package org.estudantinder.features.School.CreateSchool;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;

import org.estudantinder.entities.School;
import org.estudantinder.repositories.SchoolsRepository;

@ApplicationScoped
public class Feature {

    @Inject
    SchoolsRepository schoolsRepository;

    public void execute(DTO data) throws Exception {
        School userAlreadyExists = schoolsRepository.findByName(data.name);

        if (userAlreadyExists != null) {
            throw new EntityExistsException("School already exists");
        }
        
        School newSchool = new School();
        
        newSchool.setName(data.name);
        newSchool.setAddress(data.address);
        newSchool.setCourses(data.courses);

        schoolsRepository.persist(newSchool);
    }
}
