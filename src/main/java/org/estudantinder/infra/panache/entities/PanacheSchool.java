package org.estudantinder.infra.panache.entities;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.estudantinder.domain.models.School;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "school")
public class PanacheSchool extends PanacheEntityBase {

    @Id
    @Column(name = "id", nullable = false)
    public UUID id;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "address", nullable = false)
    public String address;

    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    public List<PanacheCourse> courses;

    public School toSchool() {
        School school = new School();
        school.id = this.id;
        school.name = this.name;
        school.address = this.address;
        school.courses = this.courses
            .stream()
            .map(course -> course.toCourse())
            .collect(Collectors.toList());

        return school;
    }
}
