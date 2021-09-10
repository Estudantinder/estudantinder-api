package org.estudantinder.features.Admins.ShowAdmin;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Admin;
import org.estudantinder.features.Admins.ShowAdmin.DTO.ShowAdmin;
import org.estudantinder.repositories.AdminsRepository;

@ApplicationScoped
public class Feature {

    @Inject
    AdminsRepository adminsRepository;
    
    private void treatAdminNotFound(Admin admin) {
        if(admin == null) {
            throw new NotFoundException("Admin não encontrado");
        }
    } 

    public ShowAdmin execute(JsonWebToken jwt) throws Exception {
        Long adminId = Long.parseLong(jwt.getClaim("id").toString());
        Admin authenticatedAdmin = adminsRepository.findById(adminId);
        
        treatAdminNotFound(authenticatedAdmin);

        ShowAdmin dto = new ShowAdmin();

        dto.id = authenticatedAdmin.getId();
        dto.email = authenticatedAdmin.getEmail();
        dto.name =  authenticatedAdmin.getName();

        return dto;

    }
}
