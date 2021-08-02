package org.estudantinder.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import io.quarkus.elytron.security.common.BcryptUtil;

@Entity
// O nome da entidade é "Users", pois "User" é uma palavra reservada
public class Users {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int school_year;

    @Column(nullable = false)
    private LocalDate birth_date;

    @Column(nullable = false)
    private String bio;

    private String gender;

    @Column(nullable = false)
    private int shift;

    @Column(nullable = false)
    private char classroom;

    private String[] photos;

    private Boolean isAdmin = false;

    @ManyToMany
    private List<Subject> subjects; 

    @ManyToOne
    private Course course;

    @OneToOne(cascade = CascadeType.ALL)
    private Contacts contacts;

    @OneToOne(cascade = CascadeType.ALL)
    private Preferences preferences;

    public String getPassword() {  
        return password;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }

    public char getClassroom() {
        return classroom;
    }

    public void setClassroom(char classroom) {
        this.classroom = classroom;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }

    public Contacts getContacts() {
        return contacts;
    }

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    public Long getId() {
        return id;
    }

    public String[] getPhotos() {
        return photos;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public int getSchool_year() {
        return school_year;
    }

    public void setSchool_year(int school_year) {
        this.school_year = school_year;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = BcryptUtil.bcryptHash(password);
    }

}
