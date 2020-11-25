package org.estudantinder.features.Users.common;

import java.time.LocalDate;

import org.estudantinder.entities.Course;
import org.estudantinder.entities.School;
import org.estudantinder.entities.Student;

public class User {
    public String name;
    public String biography;
    public LocalDate birthday;
    public String gender;
    public String schoolShift;
    public int schoolYear;
    public String[] photos;
    public String[] favoriteSubjects;
    public Course course;
    public School school;

    public static User mapStudentToUser(Student student) {
        User user = new User();
        user.name = student.getName();
        user.biography = student.getBiography();
        user.birthday = student.getBirthday();
        user.gender = student.getGender();
        user.schoolShift = student.getSchoolShift();
        user.schoolYear = student.getSchoolYear();
        user.photos = student.getPhotos();
        user.favoriteSubjects = student.getFavoriteSubjects();
        user.course = student.getCourse();
        user.school = student.getCourse().getSchool();
        return user;
    }
}
