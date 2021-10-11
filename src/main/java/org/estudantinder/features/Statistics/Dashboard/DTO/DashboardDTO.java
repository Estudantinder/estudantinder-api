package org.estudantinder.features.Statistics.Dashboard.DTO;

import java.util.List;

public class DashboardDTO {
    public List<UsersPerCourseDTO> users_per_course;
    public List<UsersPerSchoolDTO> users_per_school;
    public List<UsersPerSchoolYearDTO> users_per_school_year;

    public Double user_match_percent;
}
