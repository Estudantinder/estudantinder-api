package org.estudantinder.features.Students.Likes.CreateLike;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Likes;
import org.estudantinder.entities.Match;
import org.estudantinder.entities.Users;
import org.estudantinder.features.Students.common.MatchReturn;
import org.estudantinder.repositories.LikesRepository;
import org.estudantinder.repositories.MatchsRepository;
import org.estudantinder.repositories.UsersRepository;

import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class Feature {

    @Inject
    LikesRepository likesRepository;
    
    @Inject
    MatchsRepository matchsRepository;

    @Inject
    UsersRepository usersRepository;
    

    void throwExceptionIfLikeAlreadyExists(Users senderUser, Users receiverUser) {
        Likes likeAlreadyExists = likesRepository.find("sender = :sender and receiver = :receiver",
            Parameters.with("sender", senderUser).and("receiver", receiverUser)).firstResult();

        if(likeAlreadyExists != null) {
            throw new EntityExistsException("Like Already Exists");
        }
    }

    void throwExceptionIfUsersArentValid(Users senderUser, Users receiverUser) {
        if(senderUser == null) {
            throw new NotFoundException("Sender User id not found");
        } if(receiverUser == null) {
            throw new NotFoundException("Receiver User id not found");
        }
    }
    
    void throwExceptionIfUsersAreEqual(Long senderId, Long receiverId) {
        if(senderId.equals(receiverId)) {
            throw new BadRequestException("User can't like himself");
        }
    }

    Likes createLike(Users senderUser, Users receiverUser) {
        Likes newLike = new Likes();

        newLike.setSender(senderUser);
        newLike.setReceiver(receiverUser);

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

        throwExceptionIfUsersAreEqual(senderId, receiverId);

        Users senderUser = usersRepository.findById(senderId);
        Users receiverUser = usersRepository.findById(receiverId);
  
        throwExceptionIfUsersArentValid(senderUser, receiverUser);
        throwExceptionIfLikeAlreadyExists(senderUser, receiverUser);
        
        Likes createdLike = createLike(senderUser, receiverUser);

        Match createdMatch = createMatchIfMutualLikeExists(createdLike);

        if(createdMatch != null) {
            return MatchReturn.mapToMatchReturn(createdMatch.getLike().getReceiver(), createdMatch.getId());
        }

        return null;
    }
}
