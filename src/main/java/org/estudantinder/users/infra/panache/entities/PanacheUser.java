package org.estudantinder.users.infra.panache.entities;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.estudantinder.schools.infra.panache.entities.PanacheCourse;
import org.estudantinder.users.domain.models.User;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "app_user")
public class PanacheUser extends PanacheEntityBase {

    @Id
    public UUID id;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String password;

    @Column(nullable = false)
    public String email;

    @Column(nullable = false)
    public Date birth_date;

    @Column(nullable = false)
    public String bio;

    public String gender;

    @Column(nullable = false)
    public int school_year;

    @Column(nullable = false)
    public int shift;

    @Column(nullable = false)
    public char classroom;

    public String[] photos;

    @OneToOne(cascade = CascadeType.ALL)
    public PanacheContacts contacts;

    @OneToOne(cascade = CascadeType.ALL)
    public PanachePreferences preferences;

    @ManyToOne()
    public PanacheCourse course;

    public User toUser() {
        User user = new User();
        user.id = this.id;
        user.name = this.name;
        user.password = this.password;
        user.email = this.email;
        user.birth_date = this.birth_date;
        user.bio = this.bio;
        user.gender = this.gender;
        user.school_year = this.school_year;
        user.shift = this.shift;
        user.classroom = this.classroom;
        user.photos = this.photos;
        if(this.contacts != null)
            user.contacts = this.contacts.toContacts();
        if(this.preferences != null)
            user.preferences = this.preferences.toPreferences();
        if(this.course != null)
            user.course = this.course.toCourse();

        return user;
    }
}
