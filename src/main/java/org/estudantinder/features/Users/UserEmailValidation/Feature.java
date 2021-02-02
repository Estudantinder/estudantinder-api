package org.estudantinder.features.Users.UserEmailValidation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;

import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    UsersRepository usersRepository;

    void treatEmailAlreadyInUse(boolean isEmailAlreadyInUse) {
        if (isEmailAlreadyInUse) {
            throw new EntityExistsException("Email Already In Use");
        }
    }

    public void execute(DTO data) throws Exception {
        boolean isEmailAlreadyInUse = usersRepository.isEmailAlreadyInUse(data.email);

        treatEmailAlreadyInUse(isEmailAlreadyInUse);

    }
}
