package org.estudantinder.users.infra.panache.entities;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.estudantinder.schools.infra.panache.entities.PanacheCourse;
import org.estudantinder.schools.infra.panache.entities.PanacheSchool;
import org.estudantinder.users.domain.models.Preferences;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name= "preferences")
public class PanachePreferences extends PanacheEntityBase {
    @Id
    public UUID id;

    public int school_year;
    public int shift;

    @ManyToOne
    public PanacheCourse course;
    @ManyToOne
    public PanacheSchool school;

    public Preferences toPreferences() {
        Preferences preferences = new Preferences();
        preferences.id = this.id;
        preferences.school_year = this.school_year;
        preferences.shift = this.shift;
        if(this.course != null)
            preferences.course = this.course.toCourse();
        if(this.school != null)
            preferences.school = this.school.toSchool();

        return preferences;
    }
}
