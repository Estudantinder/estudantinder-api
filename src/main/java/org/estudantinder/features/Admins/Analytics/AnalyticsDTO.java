package org.estudantinder.features.Admins.Analytics;

import java.util.List;

import org.estudantinder.features.Admins.Analytics.FilterUsage.FilterUsageDTO;
import org.estudantinder.features.Admins.Analytics.SubjectsStatistics.SubjectStatisticsDTO;

public class AnalyticsDTO {
    public List<SubjectStatisticsDTO> subject_statistics;
    public Long number_of_users;
    public List<FilterUsageDTO> filter_usage;
}
