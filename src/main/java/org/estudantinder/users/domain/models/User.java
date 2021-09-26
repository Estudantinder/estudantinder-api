package org.estudantinder.users.domain.models;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.estudantinder.schools.domain.models.Course;

public class User {
    public UUID id;

    public String name;
    @JsonIgnore
    public String password;
    public String email;
    public Date birth_date;
    public String bio;
    public String gender;
    public int school_year;
    public int shift;
    public char classroom;
    public String[] photos;

    public Course course;
    public Contacts contacts;
    public Preferences preferences;
}
