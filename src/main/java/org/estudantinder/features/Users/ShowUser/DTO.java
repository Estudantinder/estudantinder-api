package org.estudantinder.features.Users.ShowUser;

import java.time.LocalDate;
import java.util.List;

import org.estudantinder.entities.Contacts;
import org.estudantinder.entities.Course;
import org.estudantinder.entities.Preferences;
import org.estudantinder.entities.School;
import org.estudantinder.entities.Subject;
import org.estudantinder.entities.User;

public class DTO {
    public Long id;

    public String name;

    public String email;

    public int school_year;

    public LocalDate birth_date;

    public String bio;

    public String gender;

    public int shift;

    public char classroom;

    public String[] photos;

    public List<Subject> subjects; 

    public School school;

    public Course course;

    public Contacts contacts;

    public Preferences preferences;

    public static DTO mapUserToDTO(User user) {
        DTO data = new DTO();

        data.id = user.getId();
        data.name = user.getName();
        data.email = user.getEmail();
        data.school_year = user.getSchool_year();
        data.birth_date = user.getBirth_date();
        data.bio = user.getBio();
        data.gender = user.getGender();
        data.shift = user.getShift();
        data.classroom = user.getClassroom();
        data.photos = user.getPhotos();
        data.subjects = user.getSubjects();
        data.school = user.getCourse().getSchool();
        data.course = user.getCourse();
        data.contacts = user.getContacts();
        data.preferences = user.getPreferences();

        return data;
    } 
}
