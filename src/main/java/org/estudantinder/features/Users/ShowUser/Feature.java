package org.estudantinder.features.Users.ShowUser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Users;

import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    UsersRepository usersRepository;
    
    private void treatInvalidID(Users user) {
        if(user == null) {
            throw new NotFoundException("Token ID not valid");
        }
    } 

    public Users execute(JsonWebToken jwt) throws Exception {
        Long userId = Long.parseLong(jwt.getClaim("id").toString());
        Users authenticatedUser = usersRepository.findById(userId);
        
        treatInvalidID(authenticatedUser);

        return authenticatedUser;
    }
}
