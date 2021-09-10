package org.estudantinder.features.Statistics.Dashboard.DTO;

import java.util.List;

public class DashboardDTO {
    public List<UsersPerCourseDTO> usersPerCourse;
    public List<UsersPerSchoolDTO> usersPerSchool;
    public List<UsersPerSchoolYearDTO> usersPerSchoolYear;

    public Double userMatchPercent;
}
