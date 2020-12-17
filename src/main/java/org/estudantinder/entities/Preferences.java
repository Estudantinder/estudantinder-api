package org.estudantinder.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Preferences {

    @Id
    @GeneratedValue
    private Long id;

    private int school_year;
    private String shift;
    private String gender;

    @ManyToOne
    private Course course;

    public int getSchool_year() {
        return school_year;
    }

    public Long getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public void setSchool_year(int school_year) {
        this.school_year = school_year;
    }


}
