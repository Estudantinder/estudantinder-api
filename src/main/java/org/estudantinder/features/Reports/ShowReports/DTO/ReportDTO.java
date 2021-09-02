package org.estudantinder.features.Reports.ShowReports.DTO;

import java.time.LocalDate;
import java.util.List;

public class ReportDTO {
    public String type;
    public List<LocalDate> dates;
    public List<String> descriptions;
}
