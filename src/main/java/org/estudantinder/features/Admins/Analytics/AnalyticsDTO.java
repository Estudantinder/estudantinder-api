package org.estudantinder.features.Admins.Analytics;

import java.util.List;

import org.estudantinder.features.Admins.Analytics.FilterUsage.FilterUsageDTO;
import org.estudantinder.features.Admins.Analytics.NumberOfUsers.NumberOfUsersDTO;
import org.estudantinder.features.Admins.Analytics.SubjectsStatistics.SubjectStatisticsDTO;

public class AnalyticsDTO {
    public List<SubjectStatisticsDTO> subjectStatistics;
    public NumberOfUsersDTO numberOfUsers;
    public List<FilterUsageDTO> filterUsage;
}
