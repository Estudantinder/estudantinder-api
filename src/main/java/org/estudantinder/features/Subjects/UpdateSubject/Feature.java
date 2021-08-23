package org.estudantinder.features.Subjects.UpdateSubject;

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

    Subject updateSubject(Subject subject, String name) {
        subject.setName(name);

        subjectsRepository.persist(subject);

        return subject;
    }

    public Subject execute(Long id, DTO data) throws Exception {
        Subject subject = subjectsRepository.findById(id);

        throwExceptionIfSubjectNotFound(subject);

        Subject updatedSubject = updateSubject(subject, data.name);

        return updatedSubject;
    }

}