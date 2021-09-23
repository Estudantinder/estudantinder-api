package org.estudantinder.domain.models;

import java.util.List;
import java.util.UUID;

public class School {
  public UUID id;
  public String name;
  public String address;
  public List<Course> courses;
}
