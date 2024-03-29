package org.estudantinder.features.Admins.AdminJwtValidation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Admin;
import org.estudantinder.repositories.AdminsRepository;

@ApplicationScoped
public class Feature {

    @Inject
    AdminsRepository adminsRepository;

    private void treatInvalidID(Admin admin) {
        if(admin == null) {
            throw new NotFoundException("admin no jwt não foi encontrado");
        }
    } 

    public void execute(JsonWebToken jwt) throws Exception {
        Long adminId = Long.parseLong(jwt.getClaim("id").toString());
        Admin authenticatedUser = adminsRepository.findById(adminId);
        
        treatInvalidID(authenticatedUser);
    }
}
