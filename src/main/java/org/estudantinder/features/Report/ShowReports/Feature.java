package org.estudantinder.features.Report.ShowReports;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.estudantinder.entities.Report;
import org.estudantinder.features.commom.Student;
import org.estudantinder.repositories.ReportsRepository;

@ApplicationScoped
public class Feature {

    @Inject
    ReportsRepository reportsRepository;


    public List<ShowReportDTO> mapToReportDTO(List<Report> allReports) {
        List<ShowReportDTO> reportsDTO = new ArrayList<ShowReportDTO>();

        allReports.stream().forEach(report -> {
            ShowReportDTO reportDTO = new ShowReportDTO();

            reportDTO.title = report.getTitle();
            reportDTO.description = report.getDescription();
            reportDTO.reportedStudent = Student.mapUserToStudent(report.getReportedUser());

            reportsDTO.add(reportDTO);
        });

        return reportsDTO;
    }

    public List<ShowReportDTO> execute() throws Exception {
        List<Report> allReports = reportsRepository.listAll();
        
        return mapToReportDTO(allReports);
    }
}
