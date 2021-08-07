package org.estudantinder.features.Report.ShowReports;

import java.time.LocalDate;

import org.estudantinder.features.commom.Student;

public class ShowReportDTO {
    public Long reportId;

    public String title;

    public String description;

    public Student reportedStudent;

    public LocalDate reportDate;
}
