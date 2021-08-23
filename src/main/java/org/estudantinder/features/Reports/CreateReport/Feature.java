package org.estudantinder.features.Reports.CreateReport;

import java.time.LocalDate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
        if (user == null) throw new NotFoundException("Usuário não encontrada");
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

        persistReport(reportData, reportedUser, LocalDate.now());
    }
}
