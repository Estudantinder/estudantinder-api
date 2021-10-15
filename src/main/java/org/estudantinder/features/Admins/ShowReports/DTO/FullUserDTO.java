package org.estudantinder.features.Admins.ShowReports.DTO;

import java.time.LocalDate;
import java.util.List;

import org.estudantinder.entities.Contacts;
import org.estudantinder.entities.Course;
import org.estudantinder.entities.School;
import org.estudantinder.entities.Subject;
import org.estudantinder.entities.User;

public class FullUserDTO {
    public Long id;
    public String name;
    public String bio;
    public String email;
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

    public static FullUserDTO mapToFullUserDTO(User user) {
        FullUserDTO fullUser = new FullUserDTO();
        fullUser.id = user.getId();
        fullUser.name = user.getName();
        fullUser.email = user.getEmail();
        fullUser.bio = user.getBio();
        fullUser.birth_date = user.getBirth_date();
        fullUser.gender = user.getGender();
        fullUser.shift = user.getShift();
        fullUser.classroom = user.getClassroom();
        fullUser.school_year = user.getSchool_year();
        fullUser.photos = user.getPhotos();
        fullUser.subjects = user.getSubjects();
        fullUser.course = user.getCourse();
        fullUser.school = user.getCourse().getSchool();
        fullUser.contacts = user.getContacts();
        return fullUser;
    }
}
