package org.estudantinder.features.Admins.ShowFullUser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.estudantinder.entities.User;

import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    UsersRepository usersRepository;
    
    private void treatUserNotFound(User user) {
        if(user == null) {
            throw new NotFoundException("Usuário não encontrado");
        }
    } 

    public User execute(Long userId) throws Exception {
        User user = usersRepository.findById(userId);
        
        treatUserNotFound(user);

        return user;
    }
}
