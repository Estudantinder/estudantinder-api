package org.estudantinder.domain.dto;

import javax.validation.constraints.NotEmpty;

public class CreateCourseDTO {
    @NotEmpty(message = "Nome do curso não pode ser vazio")
    public String name;
}
