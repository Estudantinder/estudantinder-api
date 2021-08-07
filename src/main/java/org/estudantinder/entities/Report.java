package org.estudantinder.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Report {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private LocalDate reportDate;
    
    @ManyToOne
    private User reportedUser;

    public Long getId() {
        return id;
    }

    public User getReportedUser() {
        return reportedUser;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate report_date) {
        this.reportDate = report_date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReportedUser(User reportedUser) {
        this.reportedUser = reportedUser;
    }
}
