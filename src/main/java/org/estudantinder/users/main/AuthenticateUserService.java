package org.estudantinder.users.main;

import javax.enterprise.context.ApplicationScoped;

import org.estudantinder.users.data.useCases.AuthenticateUserImpl;
import org.estudantinder.users.domain.dtos.authentication.AuthenticationRequestDTO;
import org.estudantinder.users.domain.dtos.authentication.AuthenticationResponseDTO;
import org.estudantinder.users.domain.useCases.AuthenticateUser;
import org.estudantinder.users.infra.panache.repository.PanacheUserRepository;

@ApplicationScoped
public class AuthenticateUserService {

    private AuthenticateUser authenticateUser;
    
    public AuthenticateUserService() {
        PanacheUserRepository panacheUserRepository = new PanacheUserRepository();
        this.authenticateUser = new AuthenticateUserImpl(panacheUserRepository);
    }

    public AuthenticationResponseDTO handle(AuthenticationRequestDTO authenticationRequestDTO) {
        return authenticateUser.authenticate(authenticationRequestDTO);
    }
}
