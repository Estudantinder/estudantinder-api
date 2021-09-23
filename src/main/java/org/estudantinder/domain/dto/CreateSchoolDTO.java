package org.estudantinder.domain.dto;

import java.util.List;

import io.smallrye.common.constraint.NotNull;

public class CreateSchoolDTO {
  @NotNull
  public String name;
  @NotNull
  public String address;
  @NotNull
  public List<CreateCourseDTO> courses;
}
