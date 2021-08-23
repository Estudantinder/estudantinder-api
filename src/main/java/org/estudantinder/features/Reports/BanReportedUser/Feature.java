package org.estudantinder.features.Reports.BanReportedUser;


import java.util.List;
import java.util.stream.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.estudantinder.entities.Match;
import org.estudantinder.entities.Report;
import org.estudantinder.entities.User;
import org.estudantinder.repositories.DislikesRepository;
import org.estudantinder.repositories.LikesRepository;
import org.estudantinder.repositories.MatchsRepository;
import org.estudantinder.repositories.ReportsRepository;
import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    ReportsRepository reportsRepository;

    @Inject
    UsersRepository usersRepository;

    @Inject
    LikesRepository likesRepository;

    @Inject
    DislikesRepository dislikesRepository;

    @Inject
    MatchsRepository matchsRepository;

    public void treatNotFoundReport(Report report) {
        if(report == null) throw new NotFoundException("Denuncia Não encontrada");
    }

    public void deleteAllUserMatchs(User user) {
        List<Match> matchsList = matchsRepository.listAll();

        Stream<Match> matchsStream = matchsList.stream();

        matchsStream.forEach(match -> {
            if(match.getLike().getSender() == user) matchsRepository.delete(match);
            if(match.getLike().getReceiver() == user) matchsRepository.delete(match);
            if(match.getMutualLike().getSender() == user) matchsRepository.delete(match);
            if(match.getMutualLike().getReceiver() == user) matchsRepository.delete(match);
        });
    }

    public void deleteAllUserLikes(User user) {
        likesRepository.findBySender(user).stream()
            .forEach(like -> likesRepository.delete(like));

        likesRepository.findByReceiver(user).stream()
            .forEach(like -> likesRepository.delete(like));
    }

    public void deleteAllUserDislikes(User user) {
        dislikesRepository.findBySender(user).stream()
            .forEach(dislike -> dislikesRepository.delete(dislike));

        dislikesRepository.findByReceiver(user).stream()
            .forEach(dislike -> dislikesRepository.delete(dislike));
    }

    public void execute(Long reportId) throws Exception {
        Report report = reportsRepository.findById(reportId);

        treatNotFoundReport(report);

        reportsRepository.findByUser(report.getReportedUser())
            .forEach(deletedReport -> reportsRepository.delete(deletedReport));

        deleteAllUserMatchs(report.getReportedUser());
        deleteAllUserLikes(report.getReportedUser());
        deleteAllUserDislikes(report.getReportedUser());

        usersRepository.delete(report.getReportedUser());
    }
}
