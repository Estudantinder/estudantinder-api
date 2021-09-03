package org.estudantinder.features.Reports.ShowReports;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.estudantinder.entities.Report;
import org.estudantinder.entities.User;
import org.estudantinder.features.Reports.ShowReports.DTO.FullUserDTO;
import org.estudantinder.features.Reports.ShowReports.DTO.ReportDTO;
import org.estudantinder.features.Reports.ShowReports.DTO.UserReportsDTO;
import org.estudantinder.repositories.ReportsRepository;
import org.estudantinder.repositories.UsersRepository;

@ApplicationScoped
public class Feature {

    @Inject
    ReportsRepository reportsRepository;

    @Inject
    UsersRepository usersRepository;

    public ReportDTO mapToReportDTO(User user, String type) {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.type = type;
        reportDTO.dates = new ArrayList<LocalDate>();
        reportDTO.descriptions = new ArrayList<String>();

        reportsRepository.findByUserAndType(user, type).stream().forEach(report -> {
            reportDTO.dates.add(report.getReportDate());
            if(type == "custom")
                reportDTO.descriptions.add(report.getDescription());
        });

        return reportDTO;
    }

    public UserReportsDTO mapToUserReportDTO(User user) {
        List<String> acceptedTypes = List.of("fakeProfile", "inappropriateContent", "spanContent", "hackedAccount",
                "selfHarm", "custom");

        UserReportsDTO userReportDTO = new UserReportsDTO();
        userReportDTO.user = FullUserDTO.mapToFullUserDTO(user);
        userReportDTO.reports = new ArrayList<ReportDTO>();

        acceptedTypes.forEach(type -> userReportDTO.reports.add(mapToReportDTO(user, type)));

        return userReportDTO;
    }

    public List<UserReportsDTO> listAllReports(List<User> allUsers) {
        List<UserReportsDTO> usersReportsDTO = new ArrayList<UserReportsDTO>();

        allUsers.stream().forEach(user -> {
            List<Report> userReports = reportsRepository.findByUser(user);
            if (!userReports.isEmpty()) {
                usersReportsDTO.add(mapToUserReportDTO(user));
            }
        });

        return usersReportsDTO;
    }

    public List<UserReportsDTO> execute() throws Exception {
        List<User> allUsers = usersRepository.listAll();

        return listAllReports(allUsers);
    }
}
