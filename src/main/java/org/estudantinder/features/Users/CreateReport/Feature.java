package org.estudantinder.features.Users.CreateReport;

import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import org.estudantinder.entities.Report;
import org.estudantinder.entities.User;
import org.estudantinder.repositories.ReportsRepository;
import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    UsersRepository usersRepository;

    @Inject
    ReportsRepository reportsRepository;

    public void treatUserDoesntExist(User user) {
        if (user == null)
            throw new NotFoundException("Usuário não encontrada");
    }

    public void treatTypeNotAccepted(String type) {
        List<String> acceptedTypes = List.of("fakeProfile", "inappropriateContent", "spanContent", "hackedAccount",
                "selfHarm", "custom");

        if(!acceptedTypes.contains(type)){
            throw new BadRequestException("Tipo de report não aceito, tipos aceitos são:"
                +"('fakeProfile', 'inappropriateContent', 'spanContent', 'hackedAccount', 'selfHarm', 'custom')");
        }
    }

    public void persistReport(CreateReportDTO reportData, User reportedUser, LocalDate reportDate) {
        Report newReport = new Report();

        newReport.setType(reportData.type);
        newReport.setDescription(reportData.description);
        newReport.setReportedUser(reportedUser);
        newReport.setReportDate(reportDate);

        reportsRepository.persist(newReport);
    }

    public void execute(CreateReportDTO reportData, Long reportedUserId) throws Exception {
        User reportedUser = usersRepository.findById(reportedUserId);

        treatUserDoesntExist(reportedUser);
        treatTypeNotAccepted(reportData.type);

        persistReport(reportData, reportedUser, LocalDate.now());
    }
}
