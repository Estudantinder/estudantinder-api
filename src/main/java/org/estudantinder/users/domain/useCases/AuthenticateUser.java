package org.estudantinder.users.domain.useCases;

import org.estudantinder.users.domain.dtos.authentication.AuthenticationRequestDTO;
import org.estudantinder.users.domain.dtos.authentication.AuthenticationResponseDTO;

public interface AuthenticateUser {
    AuthenticationResponseDTO authenticate(AuthenticationRequestDTO authenticationRequestDTO);
}
