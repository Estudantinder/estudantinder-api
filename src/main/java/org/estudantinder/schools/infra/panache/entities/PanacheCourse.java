package org.estudantinder.schools.infra.panache.entities;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.estudantinder.schools.domain.models.Course;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "course")
public class PanacheCourse extends PanacheEntityBase {

    @Id
    public UUID id;

    @Column(nullable = false) 
    public String name;

    @ManyToOne
    public PanacheSchool school;

    public Course toCourse() {
        Course course = new Course();
        course.id = this.id;
        course.name = this.name;

        return course;
    }
}
