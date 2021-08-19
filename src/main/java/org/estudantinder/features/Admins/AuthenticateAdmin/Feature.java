package org.estudantinder.features.Admins.AuthenticateAdmin;

import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.estudantinder.entities.Admin;
import org.estudantinder.features.Admins.AuthenticateAdmin.DTO.JwtDTO;
import org.estudantinder.features.Admins.AuthenticateAdmin.DTO.LoginDTO;
import org.estudantinder.repositories.AdminsRepository;
import org.wildfly.security.credential.PasswordCredential;
import org.wildfly.security.evidence.PasswordGuessEvidence;
import org.wildfly.security.password.util.ModularCrypt;

import io.quarkus.security.UnauthorizedException;
import io.smallrye.jwt.build.Jwt;

@ApplicationScoped
public class Feature {

    @Inject
    AdminsRepository adminsRepository;

    public void treatEmailNotFound(Admin authenticatedAdmin) {
        if (authenticatedAdmin == null)
            throw new NotFoundException("Email Not Found");
    }

    public void treatDifferentPassword(Admin authenticatedAdmin, String password) throws InvalidKeySpecException {
        if (!isPasswordCorrect(authenticatedAdmin.getPassword(), password))
            throw new UnauthorizedException("Wrong Password");
    }

    public boolean isPasswordCorrect(String hashDBPassword, String loginPassword) throws InvalidKeySpecException {
        PasswordCredential producedPasswordCredential = new PasswordCredential(ModularCrypt.decode(hashDBPassword));
        PasswordGuessEvidence correctPasswordEvidence = new PasswordGuessEvidence(loginPassword.toCharArray());
        return (producedPasswordCredential.verify(correctPasswordEvidence));
    }

    public String generateJwt(Admin authenticatedAdmin, Instant expireDate) {
        return Jwt.issuer("https://github.com/AdamAugustinsky").upn("estudantinder@quarkus.io").groups("Admin")
                .claim("id", authenticatedAdmin.getId()).expiresAt(expireDate).sign();
    }

    public JwtDTO execute(LoginDTO data) throws Exception {
        Admin authenticatedAdmin = adminsRepository.findByEmail(data.email);

        treatEmailNotFound(authenticatedAdmin);
        treatDifferentPassword(authenticatedAdmin, data.password);

        Instant expireDate = Instant.now().plus(15, ChronoUnit.DAYS);

        String token = generateJwt(authenticatedAdmin, expireDate);

        JwtDTO returnObject = new JwtDTO();

        returnObject.jwt = token;
        returnObject.expireDate = expireDate;

        return returnObject;
    }
}
