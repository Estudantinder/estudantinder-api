package org.estudantinder.features.Schools.UpdateSchool.DTO;


import com.fasterxml.jackson.annotation.JsonIgnore;

import org.estudantinder.entities.School;

public class CourseDTO {
    public String name;

    @JsonIgnore
    public School school;
}
