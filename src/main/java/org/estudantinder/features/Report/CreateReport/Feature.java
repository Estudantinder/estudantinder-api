package org.estudantinder.features.Report.CreateReport;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.estudantinder.entities.Report;
import org.estudantinder.entities.Users;
import org.estudantinder.repositories.ReportsRepository;
import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    UsersRepository usersRepository;

    @Inject
    ReportsRepository reportsRepository;

    public void treatUserDoesntExist(Users user) {
        if (user == null) throw new NotFoundException("User id does not exist");
    }

    public void persistReport(CreateReportDTO reportData, Users reportedUser) {
        Report newReport = new Report();

        newReport.setTitle(reportData.title);
        newReport.setDescription(reportData.description);
        newReport.setReportedUser(reportedUser);

        reportsRepository.persist(newReport);
    }

    public void execute(CreateReportDTO reportData, Long reportedUserId) throws Exception {
        Users reportedUser = usersRepository.findById(reportedUserId); 

        treatUserDoesntExist(reportedUser);

        persistReport(reportData, reportedUser);
    }
}
