package org.estudantinder.features.Users.Likes.CreateLike;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Likes;
import org.estudantinder.entities.Match;
import org.estudantinder.entities.Student;
import org.estudantinder.features.Users.common.MatchReturn;
import org.estudantinder.repositories.LikesRepository;
import org.estudantinder.repositories.MatchsRepository;
import org.estudantinder.repositories.StudentsRepository;

import io.quarkus.panache.common.Parameters;
import io.quarkus.security.UnauthorizedException;

@ApplicationScoped
public class Feature {

    @Inject
    LikesRepository likesRepository;
    
    @Inject
    MatchsRepository matchsRepository;

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
    
    void throwExceptionIfStudentAreEqual(Long senderId, Long receiverId) {
        if(senderId.equals(receiverId)) {
            throw new UnauthorizedException("Student can't like himself");
        }
    }

    Likes createLike(Student senderStudent, Student receiverStudent) {
        Likes newLike = new Likes();

        newLike.setSender(senderStudent);
        newLike.setReceiver(receiverStudent);

        likesRepository.persist(newLike);

        return newLike;
    }

    Match createMatch(Likes like, Likes mutualLike) {
        Match newMatch = new Match();

        newMatch.setLike(like);
        newMatch.setMutualLike(mutualLike);

        matchsRepository.persist(newMatch);
        
        return newMatch;
    }

    Match createMatchIfMutualLikeExists(Likes like) {
        Likes mutualLike = likesRepository.find("sender = :sender and receiver = :receiver",
            Parameters.with("sender", like.getReceiver()).and("receiver", like.getSender())).firstResult();

        if(mutualLike != null) {
            return createMatch(like, mutualLike);
        }

        return null;
    }

    public MatchReturn execute(JsonWebToken jwt, Long receiverId) throws Exception {
        Long senderId = Long.parseLong(jwt.getClaim("id").toString());

        throwExceptionIfStudentAreEqual(senderId, receiverId);

        Student senderStudent = studentsRepository.findById(senderId);
        Student receiverStudent = studentsRepository.findById(receiverId);
  
        throwExceptionIfStudentsArentValid(senderStudent, receiverStudent);
        throwExceptionIfLikeAlreadyExists(senderStudent, receiverStudent);
        
        Likes createdLike = createLike(senderStudent, receiverStudent);

        Match createdMatch = createMatchIfMutualLikeExists(createdLike);

        if(createdMatch != null) {
            return MatchReturn.mapToMatchReturn(createdMatch.getLike().getReceiver(), createdMatch.getId());
        }

        return null;
    }
}
