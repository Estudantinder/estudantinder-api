package org.estudantinder.features.Subjects.DeleteSubject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.estudantinder.entities.Subject;
import org.estudantinder.repositories.SubjectsRepository;

@ApplicationScoped
public class Feature {

    @Inject
    SubjectsRepository subjectsRepository;
    
    void throwExceptionIfSubjectNotFound(Subject subject) {
        if(subject == null) {
            throw new NotFoundException("Materia não encontrada");
        }
    }
    
    public void execute(Long id) throws Exception {
        Subject subject = subjectsRepository.findById(id);
        
        throwExceptionIfSubjectNotFound(subject);

        subjectsRepository.delete(subject);
    }
}
