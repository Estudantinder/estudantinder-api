package org.estudantinder.features.Students.Dislikes.CreateDislike;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Dislike;
import org.estudantinder.entities.Users;
import org.estudantinder.repositories.DislikesRepository;
import org.estudantinder.repositories.UsersRepository;

import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class Feature {

    @Inject
    DislikesRepository dislikesRepository;

    @Inject
    UsersRepository usersRepository;
    

    void treatDislikeAlreadyExists(Users senderUser, Users receiverUser) {
        Dislike dislikeAlreadyExists = dislikesRepository.find("sender = :sender and receiver = :receiver",
            Parameters.with("sender", senderUser).and("receiver", receiverUser)).firstResult();

        if(dislikeAlreadyExists != null) {
            throw new EntityExistsException("Dislike Already Exists");
        }
    }

    void treatfUsersArentValid(Users senderUser, Users receiverUser) {
        if(senderUser == null) {
            throw new NotFoundException("Sender User id not found");
        } if(receiverUser == null) {
            throw new NotFoundException("Receiver User id not found");
        }
    }
    
    void treatUsersAreEqual(Long senderId, Long receiverId) {
        if(senderId.equals(receiverId)) {
            throw new BadRequestException("User can't dislike himself");
        }
    }

    Dislike createLike(Users senderUser, Users receiverUser) {
        Dislike newDislike = new Dislike();

        newDislike.setSender(senderUser);
        newDislike.setReceiver(receiverUser);

        dislikesRepository.persist(newDislike);

        return newDislike;
    }

    public void execute(JsonWebToken jwt, Long receiverId) throws Exception {
        Long senderId = Long.parseLong(jwt.getClaim("id").toString());

        treatUsersAreEqual(senderId, receiverId);

        Users senderUser = usersRepository.findById(senderId);
        Users receiverUser = usersRepository.findById(receiverId);
  
        treatfUsersArentValid(senderUser, receiverUser);
        treatDislikeAlreadyExists(senderUser, receiverUser);
        
        createLike(senderUser, receiverUser);

    }
}
