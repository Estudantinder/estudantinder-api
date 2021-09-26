package org.estudantinder.users.domain.models;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.estudantinder.schools.domain.models.Course;
import org.estudantinder.schools.domain.models.School;

public class Preferences {
    @JsonIgnore
    public UUID id;

    public int school_year;
    public int shift;

    public Course course;
    public School school;
}
