package org.estudantinder.features.Users.Matchs.DeleteMatch;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Match;
import org.estudantinder.entities.Student;
import org.estudantinder.repositories.MatchsRepository;
import org.estudantinder.repositories.StudentsRepository;

import io.quarkus.security.UnauthorizedException;

@ApplicationScoped
public class Feature {
    
    @Inject
    MatchsRepository matchsRepository;

    @Inject
    StudentsRepository studentsRepository;

    void throwExceptionIfMatchNotValid(Match match) {
        if(match == null) {
            throw new EntityNotFoundException("Match id not found");
        }
    }

    void throwExceptionIfStudentUnautorized(Match match, Student student) {
        if( match.getLike().getSender() != student &&
            match.getMutualLike().getSender() != student) {
            
            throw new UnauthorizedException("Unauthorized, Match Ins't Yours");
        }
    }

    void throwExceptionIfStudentNotValid(Student student) {
        if(student == null) {
            throw new EntityNotFoundException("Student id Not Found");
        }
    }

    public void execute(JsonWebToken jwt ,Long matchId) throws Exception {
        Long userId = Long.parseLong(jwt.getClaim("id").toString());
        Student authenticatedStudent = studentsRepository.findById(userId);

        throwExceptionIfStudentNotValid(authenticatedStudent);

        Match match = matchsRepository.findById(matchId);

        throwExceptionIfMatchNotValid(match);
        throwExceptionIfStudentUnautorized(match, authenticatedStudent);
 
        matchsRepository.delete(match);
    }
}
