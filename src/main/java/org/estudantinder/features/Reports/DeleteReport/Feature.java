package org.estudantinder.features.Reports.DeleteReport;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import org.estudantinder.entities.Report;
import org.estudantinder.repositories.ReportsRepository;
import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    ReportsRepository reportsRepository;

    @Inject
    UsersRepository usersRepository;

    public void treatNotFoundReport(Report report) {
        if(report == null) throw new NotFoundException("Denuncia não encontrada");
    }

    public void execute(Long reportId) throws Exception {
        Report report = reportsRepository.findById(reportId);

        treatNotFoundReport(report);

        reportsRepository.delete(report);
    }
}
