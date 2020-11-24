package org.estudantinder.features.Student.AuthenticateStudent;

import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.estudantinder.entities.Student;
import org.estudantinder.repositories.StudentsRepository;
import org.wildfly.security.credential.PasswordCredential;
import org.wildfly.security.evidence.PasswordGuessEvidence;
import org.wildfly.security.password.util.ModularCrypt;

import io.quarkus.security.UnauthorizedException;
import io.smallrye.jwt.build.Jwt;

@ApplicationScoped
public class Feature {

    @Inject
    StudentsRepository studentsRepository;

    public boolean isPasswordCorrect(String hashDBPassword, String loginPassword) throws InvalidKeySpecException {
        PasswordCredential producedPasswordCredential = new PasswordCredential(ModularCrypt.decode(hashDBPassword));
        PasswordGuessEvidence correctPasswordEvidence = new PasswordGuessEvidence(loginPassword.toCharArray());
        return (producedPasswordCredential.verify(correctPasswordEvidence));
    }

    public String execute(DTO data) throws Exception {
        Student authenticatedStudent =  studentsRepository.findByEmail(data.email);

        if(authenticatedStudent == null) {
            throw new NotFoundException("Email Not Valid");
        }
        if(!isPasswordCorrect(authenticatedStudent.getPassword(), data.password)) {
            throw new UnauthorizedException("Wrong Password");
        }

        String token = Jwt.issuer("https://github.com/AdamAugustinsky")
            .upn("estudantinder@quarkus.io")
            .groups("Student")
            .claim("id", authenticatedStudent.getId())
            .expiresAt(Instant.now().plus(15, ChronoUnit.DAYS ))
            .sign();
        
        return token;
    }
}
