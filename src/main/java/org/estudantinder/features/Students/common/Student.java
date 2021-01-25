package org.estudantinder.features.Students.common;

import java.time.LocalDate;
import java.util.List;

import org.estudantinder.entities.Contacts;
import org.estudantinder.entities.Course;
import org.estudantinder.entities.School;
import org.estudantinder.entities.Subject;
import org.estudantinder.entities.Users;

public class Student {
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

    public static Student mapUserToStudent(Users user) {
        Student Student = new Student();
        Student.id = user.getId();
        Student.name = user.getName();
        Student.bio = user.getBio();
        Student.birth_date = user.getBirth_date();
        Student.gender = user.getGender();
        Student.shift = user.getShift();
        Student.classroom = user.getClassroom();
        Student.school_year = user.getSchool_year();
        Student.photos = user.getPhotos();
        Student.subjects = user.getSubjects();
        Student.course = user.getCourse();
        Student.school = user.getCourse().getSchool();
        return Student;
    }  

    public static Student mapUserToStudentWithContacts(Users user) {
        Student Student = new Student();
        Student.id = user.getId();
        Student.name = user.getName();
        Student.bio = user.getBio();
        Student.birth_date = user.getBirth_date();
        Student.gender = user.getGender();
        Student.shift = user.getShift();
        Student.classroom = user.getClassroom();
        Student.school_year = user.getSchool_year();
        Student.photos = user.getPhotos();
        Student.subjects = user.getSubjects();
        Student.course = user.getCourse();
        Student.school = user.getCourse().getSchool();
        Student.contacts = user.getContacts();
        return Student;
    }
}
