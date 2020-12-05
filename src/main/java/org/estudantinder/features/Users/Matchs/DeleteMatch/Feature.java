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

    public void execute(JsonWebToken jwt ,Long matchId) throws Exception {
        Long userId = Long.parseLong(jwt.getClaim("id").toString());
        Student authenticatedStudent = studentsRepository.findById(userId);

        if(authenticatedStudent == null) {
            throw new EntityNotFoundException("Student id Not Found");
        }

        Match match = matchsRepository.findById(matchId);
        
        if(match == null) {
            throw new EntityNotFoundException("Match id not found");
        }

        if( match.getLike().getSender() != authenticatedStudent &&
            match.getMutualLike().getSender() != authenticatedStudent) {
            
            throw new UnauthorizedException("Can't delete a match that isn't yours");
        }
 
        matchsRepository.delete(match);
    }
}
