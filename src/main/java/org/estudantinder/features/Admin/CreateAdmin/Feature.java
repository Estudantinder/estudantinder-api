package org.estudantinder.features.Admin.CreateAdmin;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Admin;
import org.estudantinder.features.Admin.CreateAdmin.DTO.AdminDTO;
import org.estudantinder.repositories.AdminsRepository;

@ApplicationScoped
public class Feature {

    @Inject
    AdminsRepository adminsRepository;

    public void treatEmailAlreadyInUse(Admin isEmailAlreadyInUse) {
        if(isEmailAlreadyInUse != null) 
            throw new EntityExistsException("Email Already In Use");
    }

    public void treatPasswordDoesntContainsNumber(String password) {
        if(checkIfPasswordDoesntContainsNumber(password))
            throw new BadRequestException("Password must contain at least 1 number");
    }

    public void treatAdminInJwtNotFound(Admin adminInJwt) {
        if(adminInJwt == null) 
            throw new NotFoundException("Admin ID in jwt was not found");
    }
    
    public boolean checkIfPasswordDoesntContainsNumber(String password) {
        //code reference https://stackoverflow.com/questions/18590901/check-if-a-string-contains-numbers-java#18590949
        if(password.matches(".*\\d.*")) {
            return false;
        }

        return true;
    }

    public Admin setNewAdmin(AdminDTO admin) {
        Admin newAdmin = new Admin();

        newAdmin.setName(admin.name);
        newAdmin.setEmail(admin.email);
        newAdmin.setPassword(admin.password);
        
        return newAdmin;
    }

    public void execute(AdminDTO data, JsonWebToken jwt) throws Exception {
        Long adminId = Long.parseLong(jwt.getClaim("id").toString());
        Admin adminInJwt = adminsRepository.findById(adminId);

        Admin isEmailAlreadyInUse = adminsRepository.findByEmail(data.email);

        treatAdminInJwtNotFound(adminInJwt);

        treatEmailAlreadyInUse(isEmailAlreadyInUse);
        treatPasswordDoesntContainsNumber(data.password);
        
        Admin newAdmin = setNewAdmin(data);
        adminsRepository.persist(newAdmin);
    }
}
