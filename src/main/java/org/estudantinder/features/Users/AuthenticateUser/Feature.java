package org.estudantinder.features.Users.AuthenticateUser;

import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.estudantinder.entities.User;
import org.estudantinder.features.Users.AuthenticateUser.DTO.JwtDTO;
import org.estudantinder.features.Users.AuthenticateUser.DTO.LoginDTO;
import org.estudantinder.repositories.UsersRepository;
import org.wildfly.security.credential.PasswordCredential;
import org.wildfly.security.evidence.PasswordGuessEvidence;
import org.wildfly.security.password.util.ModularCrypt;

import io.quarkus.security.UnauthorizedException;
import io.smallrye.jwt.build.Jwt;

@ApplicationScoped
public class Feature {

    @Inject
    UsersRepository usersRepository;

    public void treatEmailNotFound(User authenticatedUser) {
        if (authenticatedUser == null)
            throw new NotFoundException("Email Not Found");
    }

    public void treatDifferentPassword(User authenticatedUser, String password) throws InvalidKeySpecException {
        if (!isPasswordCorrect(authenticatedUser.getPassword(), password))
            throw new UnauthorizedException("Wrong Password");
    }

    public boolean isPasswordCorrect(String hashDBPassword, String loginPassword) throws InvalidKeySpecException {
        PasswordCredential producedPasswordCredential = new PasswordCredential(ModularCrypt.decode(hashDBPassword));
        PasswordGuessEvidence correctPasswordEvidence = new PasswordGuessEvidence(loginPassword.toCharArray());
        return (producedPasswordCredential.verify(correctPasswordEvidence));
    }

    public String generateJwt(User authenticatedUser, Instant expireDate) {
        return Jwt.issuer("https://github.com/AdamAugustinsky").upn("estudantinder@quarkus.io").groups("User")
                .claim("id", authenticatedUser.getId()).expiresAt(expireDate).sign();
    }

    public JwtDTO execute(LoginDTO data) throws Exception {
        User authenticatedUser = usersRepository.findByEmail(data.email);

        treatEmailNotFound(authenticatedUser);
        treatDifferentPassword(authenticatedUser, data.password);

        Instant expireDate = Instant.now().plus(15, ChronoUnit.DAYS);

        String token = generateJwt(authenticatedUser, expireDate);

        JwtDTO returnObject = new JwtDTO();

        returnObject.jwt = token;
        returnObject.expireDate = expireDate;

        return returnObject;
    }
}
