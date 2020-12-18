package org.estudantinder.features.Users.common;

import java.time.LocalDate;
import java.util.List;

import org.estudantinder.entities.Contacts;
import org.estudantinder.entities.Course;
import org.estudantinder.entities.School;
import org.estudantinder.entities.Student;
import org.estudantinder.entities.Subject;

public class User {
    public Long id;
    public String name;
    public String bio;
    public LocalDate birth_date;
    public String gender;
    public int shift;
    public int school_year;
    public char classroom;
    public String[] photos;
    public List<Subject> subjects;
    public Course course;
    public School school;
    public Contacts contacts;

    public static User mapStudentToUser(Student student) {
        User user = new User();
        user.id = student.getId();
        user.name = student.getName();
        user.bio = student.getBio();
        user.birth_date = student.getBirth_date();
        user.gender = student.getGender();
        user.shift = student.getShift();
        user.classroom = student.getClassroom();
        user.school_year = student.getSchool_year();
        user.photos = student.getPhotos();
        user.subjects = student.getSubjects();
        user.course = student.getCourse();
        user.school = student.getCourse().getSchool();
        return user;
    }  

    public static User mapStudentToUserWithContacts(Student student) {
        User user = new User();
        user.id = student.getId();
        user.name = student.getName();
        user.bio = student.getBio();
        user.birth_date = student.getBirth_date();
        user.gender = student.getGender();
        user.shift = student.getShift();
        user.classroom = student.getClassroom();
        user.school_year = student.getSchool_year();
        user.photos = student.getPhotos();
        user.subjects = student.getSubjects();
        user.course = student.getCourse();
        user.school = student.getCourse().getSchool();
        user.contacts = student.getContacts();
        return user;
    }
}
