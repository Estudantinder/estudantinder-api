package org.estudantinder.features.Subjects.DeleteSubject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.estudantinder.entities.Subject;
import org.estudantinder.repositories.SubjectsRepository;

@ApplicationScoped
public class Feature {

    @Inject
    SubjectsRepository subjectsRepository;
    
    void throwExceptionIfSubjectNotFound(Subject subject) {
        if(subject == null) {
            throw new EntityNotFoundException("Subject id not found");
        }
    }
    
    public void execute(Long id) throws Exception {
        Subject subject = subjectsRepository.findById(id);
        
        throwExceptionIfSubjectNotFound(subject);

        subjectsRepository.delete(subject);
    }
}
