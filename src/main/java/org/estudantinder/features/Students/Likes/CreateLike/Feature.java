package org.estudantinder.features.Students.Likes.CreateLike;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Like;
import org.estudantinder.entities.Match;
import org.estudantinder.entities.User;
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
    

    void throwExceptionIfLikeAlreadyExists(User senderUser, User receiverUser) {
        Like likeAlreadyExists = likesRepository.find("sender = :sender and receiver = :receiver",
            Parameters.with("sender", senderUser).and("receiver", receiverUser)).firstResult();

        if(likeAlreadyExists != null) {
            throw new EntityExistsException("Like já existe");
        }
    }

    void throwExceptionIfUsersArentValid(User senderUser, User receiverUser) {
        if(senderUser == null) {
            throw new NotFoundException("Remetente não encontrado");
        } if(receiverUser == null) {
            throw new NotFoundException("Destinatario não encontrado");
        }
    }
    
    void throwExceptionIfUsersAreEqual(Long senderId, Long receiverId) {
        if(senderId.equals(receiverId)) {
            throw new BadRequestException("Usuário não pode dar like em si mesmo");
        }
    }

    Like createLike(User senderUser, User receiverUser) {
        Like newLike = new Like();

        newLike.setSender(senderUser);
        newLike.setReceiver(receiverUser);

        likesRepository.persist(newLike);

        return newLike;
    }

    Match createMatch(Like like, Like mutualLike) {
        Match newMatch = new Match();

        newMatch.setLike(like);
        newMatch.setMutualLike(mutualLike);

        matchsRepository.persist(newMatch);
        
        return newMatch;
    }

    Match createMatchIfMutualLikeExists(Like like) {
        Like mutualLike = likesRepository.find("sender = :sender and receiver = :receiver",
            Parameters.with("sender", like.getReceiver()).and("receiver", like.getSender())).firstResult();

        if(mutualLike != null) {
            return createMatch(like, mutualLike);
        }

        return null;
    }

    public MatchReturn execute(JsonWebToken jwt, Long receiverId) throws Exception {
        Long senderId = Long.parseLong(jwt.getClaim("id").toString());

        throwExceptionIfUsersAreEqual(senderId, receiverId);

        User senderUser = usersRepository.findById(senderId);
        User receiverUser = usersRepository.findById(receiverId);
  
        throwExceptionIfUsersArentValid(senderUser, receiverUser);
        throwExceptionIfLikeAlreadyExists(senderUser, receiverUser);
        
        Like createdLike = createLike(senderUser, receiverUser);

        Match createdMatch = createMatchIfMutualLikeExists(createdLike);

        if(createdMatch != null) {
            return MatchReturn.mapToMatchReturn(createdMatch.getLike().getReceiver(), createdMatch.getId());
        }

        return null;
    }
}
