package org.estudantinder.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Report {

    @Id
    @GeneratedValue
    private Long id;

    private String type;

    private String description;

    private LocalDate reportDate;
    
    @ManyToOne
    @JsonIgnore
    private User reported_user;

    public Long getId() {
        return id;
    }

    public User getReportedUser() {
        return reported_user;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setReportedUser(User reported_user) {
        this.reported_user = reported_user;
    }
}
