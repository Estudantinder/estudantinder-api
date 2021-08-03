package org.estudantinder.entities;

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
    
    @ManyToOne
    private Users reportedUser;

    public Users getReportedUser() {
        return reportedUser;
    }

    public String getDescription() {
        return description;
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

    public void setReportedUser(Users reportedUser) {
        this.reportedUser = reportedUser;
    }
}
