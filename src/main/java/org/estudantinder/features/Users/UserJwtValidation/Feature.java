package org.estudantinder.features.Users.UserJwtValidation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.User;
import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    UsersRepository usersRepository;

    private void treatInvalidID(User user) {
        if(user == null) {
            throw new NotFoundException("Usuário Não encontrado");
        }
    } 

    public void execute(JsonWebToken jwt) throws Exception {
        Long userId = Long.parseLong(jwt.getClaim("id").toString());
        User authenticatedUser = usersRepository.findById(userId);
        
        treatInvalidID(authenticatedUser);
    }
}
