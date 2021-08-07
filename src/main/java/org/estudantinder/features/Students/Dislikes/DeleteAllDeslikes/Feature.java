package org.estudantinder.features.Students.Dislikes.DeleteAllDeslikes;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.User;
import org.estudantinder.repositories.DislikesRepository;
import org.estudantinder.repositories.UsersRepository;


@ApplicationScoped
public class Feature {

    @Inject
    DislikesRepository dislikesRepository;

    @Inject
    UsersRepository usersRepository;
    

    void treatInvalidUserID(User authenticatedUser) {
        if(authenticatedUser == null) {
            throw new NotFoundException(" User id not found");
        }
    }

    private void deleteAllLikes(User authenticatedUser) {
        dislikesRepository.delete("sender", authenticatedUser);
    }

    public void execute(JsonWebToken jwt) throws Exception {
        Long senderId = Long.parseLong(jwt.getClaim("id").toString());
        User authenticatedUser = usersRepository.findById(senderId);
 
        treatInvalidUserID(authenticatedUser);
        
        deleteAllLikes(authenticatedUser);
    }
}
