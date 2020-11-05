package org.estudantinder.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class School {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  private String address;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Course> courses;

  public String getName() {
    return name;
  }

  public List<Course> getCourses() {
    return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

  public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
	}

}
