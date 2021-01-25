package org.estudantinder.features.Students.Matchs.ShowMatchs;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Match;
import org.estudantinder.entities.Users;
import org.estudantinder.features.Students.common.MatchReturn;
import org.estudantinder.repositories.MatchsRepository;
import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    MatchsRepository matchsRepository;
    
    @Inject
    UsersRepository usersRepository;

    Stream<Match> getUserMatchs(Stream<Match> allMatchs, Users authenticatedUser ) {
        Stream<Match> userMatchs = allMatchs.filter(
            match -> 
                match.getLike().getSender() == authenticatedUser || 
                match.getMutualLike().getSender() == authenticatedUser);

        return userMatchs;
    }

    Stream<MatchReturn> getUserMatchReceivers(Stream<Match> userMatchs, Users authenticatedUser ) {
        Stream<MatchReturn> userMatchsReceivers = userMatchs.map(match -> {
            if(match.getLike().getSender() == authenticatedUser) {
                return MatchReturn.mapToMatchReturn(match.getLike().getReceiver(), match.getId());
            }
            
            return MatchReturn.mapToMatchReturn(match.getMutualLike().getReceiver(), match.getId());
        });

        return userMatchsReceivers;
    }
    
    List<MatchReturn> listUserMatchReceivers(Stream<Match> allMatchs, Users authenticatedUser) {
        Stream<Match> studentMatchs = getUserMatchs(allMatchs, authenticatedUser);
        Stream<MatchReturn> userMatchReceivers = getUserMatchReceivers(studentMatchs, authenticatedUser);

        return userMatchReceivers.collect(Collectors.toList());
    }

    void throwErrorIfStudentNotValid(Users user) {
        if(user == null) {
            throw new NotFoundException("User id not found");
        }
    }

    public List<MatchReturn> execute(JsonWebToken jwt) throws Exception {
        Long userId = Long.parseLong(jwt.getClaim("id").toString());
        Users authenticatedUser = usersRepository.findById(userId);
        
        throwErrorIfStudentNotValid(authenticatedUser);
        
        Stream<Match> allMatchs = matchsRepository.streamAll();
        return listUserMatchReceivers(allMatchs, authenticatedUser);
    }
    
}