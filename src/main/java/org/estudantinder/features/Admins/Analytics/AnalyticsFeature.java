package org.estudantinder.features.Admins.Analytics;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.estudantinder.features.Admins.Analytics.FilterUsage.FilterUsageFeature;
import org.estudantinder.features.Admins.Analytics.NumberOfUsers.NumberOfUsersFeature;
import org.estudantinder.features.Admins.Analytics.SubjectsStatistics.SubjectsStatisticsFeature;

@ApplicationScoped
public class AnalyticsFeature {

    @Inject
    FilterUsageFeature filterUsageFeature;

    @Inject
    NumberOfUsersFeature numberOfUsersFeature;

    @Inject
    SubjectsStatisticsFeature subjectsStatisticsFeature;

    public AnalyticsDTO execute() {
        AnalyticsDTO analyticsDTO = new AnalyticsDTO();

        analyticsDTO.subject_statistics = subjectsStatisticsFeature.execute();
        analyticsDTO.number_of_users = numberOfUsersFeature.execute();
        analyticsDTO.filter_usage = filterUsageFeature.execute();

        return analyticsDTO;
    }
}
