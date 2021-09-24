package org.estudantinder.schools.domain.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;


public class CreateSchoolDTO {
  @NotEmpty(message = "Nome não pode ser vazio")
  public String name;
  @NotEmpty(message = "Endereço não pode ser vazio")
  public String address;
  @NotEmpty(message = "Campo Cursos não pode ser vazio")
  public List<CreateCourseDTO> courses;
}
