package org.estudantinder.features.Users.Likes.CreateLike;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Likes;
import org.estudantinder.entities.Student;
import org.estudantinder.repositories.LikesRepository;
import org.estudantinder.repositories.StudentsRepository;

import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class Feature {

    @Inject
    LikesRepository likesRepository;

    @Inject
    StudentsRepository studentsRepository;

    void throwExceptionIfLikeAlreadyExists(Student senderStudent, Student receiverStudent) {
        Likes likeAlreadyExists = likesRepository.find("sender = :sender and receiver = :receiver",
        Parameters.with("sender", senderStudent).and("receiver", receiverStudent)).firstResult();

        if(likeAlreadyExists != null) {
            throw new EntityExistsException("Like Already Exists");
        }
    }

    void throwExceptionIfStudentsArentValid(Student senderStudent, Student receiverStudent) {
        if(senderStudent == null) {
            throw new NotFoundException("Sender student id not found");
        } if(receiverStudent == null) {
            throw new NotFoundException("Receiver student id not found");
        }
    }

    void createLike(Student senderStudent, Student receiverStudent) {
        Likes newLike = new Likes();

        newLike.setSender(senderStudent);
        newLike.setReceiver(receiverStudent);

        likesRepository.persist(newLike);
    }

    public void execute(JsonWebToken jwt, Long receiverId) throws Exception {
        Long senderId = Long.parseLong(jwt.getClaim("id").toString());
        Student senderStudent = studentsRepository.findById(senderId);
        Student receiverStudent = studentsRepository.findById(receiverId);

        throwExceptionIfStudentsArentValid(senderStudent, receiverStudent);
        throwExceptionIfLikeAlreadyExists(senderStudent, receiverStudent);
        
        createLike(senderStudent, receiverStudent);
    }
}
