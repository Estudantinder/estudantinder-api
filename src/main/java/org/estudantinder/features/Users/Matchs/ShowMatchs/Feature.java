package org.estudantinder.features.Users.Matchs.ShowMatchs;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.estudantinder.entities.Match;
import org.estudantinder.entities.Student;
import org.estudantinder.features.Users.common.MatchReturn;
import org.estudantinder.repositories.MatchsRepository;
import org.estudantinder.repositories.StudentsRepository;

@ApplicationScoped
public class Feature {

    @Inject
    MatchsRepository matchsRepository;
    
    @Inject
    StudentsRepository studentsRepository;

    Stream<Match> getStudentMatchs(Stream<Match> allMatchs, Student authenticatedStudent ) {
        Stream<Match> studentMatchs = allMatchs.filter(
            match -> 
                match.getLike().getSender() == authenticatedStudent || 
                match.getMutualLike().getSender() == authenticatedStudent);

        return studentMatchs;
    }

    Stream<MatchReturn> getUserMatchReceivers(Stream<Match> studentMatchs, Student authenticatedStudent ) {
        Stream<MatchReturn> userMatchsReceivers = studentMatchs.map(match -> {
            if(match.getLike().getSender() == authenticatedStudent) {
                return MatchReturn.mapToMatchReturn(match.getLike().getReceiver(), match.getId());
            }
            
            return MatchReturn.mapToMatchReturn(match.getMutualLike().getReceiver(), match.getId());
        });

        return userMatchsReceivers;
    }
    
    List<MatchReturn> listUserMatchReceivers(Stream<Match> allMatchs, Student authenticatedStudent) {
        Stream<Match> studentMatchs = getStudentMatchs(allMatchs, authenticatedStudent);

        Stream<MatchReturn> userMatchReceivers = getUserMatchReceivers(studentMatchs, authenticatedStudent);

        return userMatchReceivers.collect(Collectors.toList());
    }

    void throwErrorIfStudentNotValid(Student student) {
        if(student == null) {
            throw new NotFoundException("User id not found");
        }
    }

    public List<MatchReturn> execute(JsonWebToken jwt) throws Exception {
        Long userId = Long.parseLong(jwt.getClaim("id").toString());
        Student authenticatedStudent = studentsRepository.findById(userId);
        
        throwErrorIfStudentNotValid(authenticatedStudent);
        
        Stream<Match> allMatchs = matchsRepository.streamAll();
        return listUserMatchReceivers(allMatchs, authenticatedStudent);
    }
    
}