package org.estudantinder.features.Subjects.CreateSubject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;

import org.estudantinder.entities.Subject;
import org.estudantinder.repositories.SubjectsRepository;

@ApplicationScoped
public class Feature {

    @Inject
    SubjectsRepository subjectsRepository;

    void throwExceptionIfSubjectAlreadyExists(Subject subject) {
        if (subject != null) {
            throw new EntityExistsException("Subject already exists");
        }
    }

    Subject createSubject(String name) {
        Subject newSubject = new Subject();
        
        newSubject.setName(name);

        subjectsRepository.persist(newSubject);

        return newSubject;
    }

    public Subject execute(DTO data) throws Exception {
        Subject alreadyExistentSubject = subjectsRepository.findByName(data.name);

        throwExceptionIfSubjectAlreadyExists(alreadyExistentSubject);
        
        Subject createdSubject = createSubject(data.name);

        return createdSubject;
    }
}
