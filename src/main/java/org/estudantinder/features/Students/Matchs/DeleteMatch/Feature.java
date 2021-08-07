package org.estudantinder.features.Students.Matchs.DeleteMatch;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Like;
import org.estudantinder.entities.Match;
import org.estudantinder.entities.User;
import org.estudantinder.repositories.LikesRepository;
import org.estudantinder.repositories.MatchsRepository;
import org.estudantinder.repositories.UsersRepository;

import io.quarkus.security.UnauthorizedException;

@ApplicationScoped
public class Feature {
    
    @Inject
    MatchsRepository matchsRepository;

    @Inject
    LikesRepository likesRepository; 

    @Inject
    UsersRepository usersRepository;

    void throwExceptionIfMatchNotValid(Match match) {
        if(match == null) {
            throw new NotFoundException("Match id not found");
        }
    }

    void throwExceptionIfUserUnautorized(Match match, User user) {
        if( match.getLike().getSender() != user &&
            match.getMutualLike().getSender() != user) {
            
            throw new UnauthorizedException("Unauthorized, Match Ins't Yours");
        }
    }

    void throwExceptionIfUserNotValid(User user) {
        if(user == null) {
            throw new NotFoundException("User id Not Found");
        }
    }

    public void execute(JsonWebToken jwt ,Long matchId) throws Exception {
        Long UserId = Long.parseLong(jwt.getClaim("id").toString());
        User authenticatedUser = usersRepository.findById(UserId);

        throwExceptionIfUserNotValid(authenticatedUser);

        Match match = matchsRepository.findById(matchId);

        throwExceptionIfMatchNotValid(match);
        throwExceptionIfUserUnautorized(match, authenticatedUser);

        matchsRepository.delete(match);

        if(match.getLike().getSender() == authenticatedUser) {
            Like like = likesRepository.findById(match.getLike().getId());
            likesRepository.delete(like);
        } else {
            Like like = likesRepository.findById(match.getMutualLike().getId());
            likesRepository.delete(like);
        }

    }
}
