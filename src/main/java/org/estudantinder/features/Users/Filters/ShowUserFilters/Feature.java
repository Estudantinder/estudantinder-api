package org.estudantinder.features.Users.Filters.ShowUserFilters;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Preferences;
import org.estudantinder.entities.User;
import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    UsersRepository usersRepository;
    
    private void treatInvalidUser(User user) {
        if(user == null) {
            throw new NotFoundException("Token ID not valid");
        }
    } 

    public Preferences execute(JsonWebToken jwt) throws Exception {
        Long userId = Long.parseLong(jwt.getClaim("id").toString());
        User authenticatedUser = usersRepository.findById(userId);
        
        treatInvalidUser(authenticatedUser);

        return authenticatedUser.getPreferences(); 
    }
}
