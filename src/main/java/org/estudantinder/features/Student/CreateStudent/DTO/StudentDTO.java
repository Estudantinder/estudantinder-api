package org.estudantinder.features.Student.CreateStudent.DTO;

import java.time.LocalDate;

public class StudentDTO {
    
    public String name;
    public String email;
    public String password;
    public int schoolYear;
    public LocalDate birthday;
    public String biography;
    public String[] photos;
    public String[] favoriteSubjects;

    public Long courseId;

    public ContactsDTO contacts;

    public PreferencesDTO preferences;
}
