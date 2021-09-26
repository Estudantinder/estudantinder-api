package org.estudantinder.users.main;

import javax.enterprise.context.ApplicationScoped;

import org.estudantinder.schools.infra.panache.repositories.PanacheCourseRepository;
import org.estudantinder.schools.infra.panache.repositories.PanacheSchoolRepository;
import org.estudantinder.users.data.useCases.CreateUserImpl;
import org.estudantinder.users.domain.dtos.CreateUserDTO;
import org.estudantinder.users.domain.models.User;
import org.estudantinder.users.domain.useCases.CreateUser;
import org.estudantinder.users.infra.panache.repository.PanacheUserRepository;

@ApplicationScoped
public class CreateUserService {

    CreateUser createUser;

    public CreateUserService() {
        PanacheUserRepository panacheUserRepository = new PanacheUserRepository();
        PanacheSchoolRepository panacheSchoolRepository = new PanacheSchoolRepository();
        PanacheCourseRepository panacheCourseRepository = new PanacheCourseRepository();

        this.createUser = new CreateUserImpl(
            panacheUserRepository,
            panacheSchoolRepository,
            panacheCourseRepository); 
    }

    public User handle(CreateUserDTO createUserDTO) {
        return createUser.create(createUserDTO);
    }
}
