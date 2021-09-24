package org.estudantinder.schools.domain.models;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Course {
  public UUID id;
  public String name;

  @JsonIgnore
  public School school;
}
