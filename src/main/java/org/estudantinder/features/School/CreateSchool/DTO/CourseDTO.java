package org.estudantinder.features.School.CreateSchool.DTO;

import javax.json.bind.annotation.JsonbTransient;

import org.estudantinder.entities.School;

public class CourseDTO {
    public String name;

    @JsonbTransient
    public School school;
}
