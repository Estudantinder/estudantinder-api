package org.estudantinder.users.data.useCases;

import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import javax.ws.rs.WebApplicationException;

import org.estudantinder.shared.utils.EncryptUtils;
import org.estudantinder.users.data.interfaces.UserRepository;
import org.estudantinder.users.domain.dtos.authentication.AuthenticationRequestDTO;
import org.estudantinder.users.domain.dtos.authentication.AuthenticationResponseDTO;
import org.estudantinder.users.domain.models.User;
import org.estudantinder.users.domain.useCases.AuthenticateUser;

import io.smallrye.jwt.build.Jwt;

public class AuthenticateUserImpl implements AuthenticateUser {

    private UserRepository userRepository;

    public AuthenticateUserImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void validatePassword(String dbPassword, String passwordToValidate) {
        try {
            if(!EncryptUtils.isPasswordEqual(dbPassword, passwordToValidate))
                throw new WebApplicationException("Senha incorreta", 401);
        } catch(InvalidKeySpecException exception) {
            throw new WebApplicationException("Erro na validação da senha", 500);
        }
    }

    private void checkUserExistence(User user) {
        if(user == null) 
            throw new WebApplicationException("Usuário não encontrado", 404);
    }

    private String generateToken(UUID id, Instant expirationDate) {
        return
           Jwt.issuer("https://github.com/Estudantinder") 
             .upn("estudantinder@quarkus.io") 
             .groups("User")
             .claim("id", id) 
             .expiresAt(expirationDate)
           .sign();
    }

    private AuthenticationResponseDTO generateResponse(UUID id, Instant expirationDate) {
        AuthenticationResponseDTO authenticationResponseDTO = new AuthenticationResponseDTO();
        authenticationResponseDTO.token = generateToken(id, expirationDate);
        authenticationResponseDTO.expirationDate = expirationDate;

        return authenticationResponseDTO;
    }

    @Override
    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO authenticationRequestDTO) {
        User user = userRepository.findByEmail(authenticationRequestDTO.email);

        checkUserExistence(user);

        validatePassword(user.password, authenticationRequestDTO.password);

        return generateResponse(
            user.id,
            Instant.now().plus(15, ChronoUnit.DAYS)
        );
    }

}
