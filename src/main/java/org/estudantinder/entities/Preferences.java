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

    private int schoolYear;
    private String schoolShift;
    private String gender;

    @ManyToOne
    private Course course;

    public int getSchoolYear() {
        return schoolYear;
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

    public String getSchoolShift() {
        return schoolShift;
    }

    public void setSchoolShift(String schoolShift) {
        this.schoolShift = schoolShift;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

}
