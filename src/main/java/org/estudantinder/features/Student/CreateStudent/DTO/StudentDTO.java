package org.estudantinder.features.Student.CreateStudent.DTO;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class StudentDTO {
    
    @NotBlank(message = "Name may not be blank")
    public String name;

    @NotBlank(message = "Email may not be blank")
    public String email;

    @NotBlank(message = "Password may not be blank")
    public String password;

    @NotNull(message = "School Year must not be null")
    @Min(message = "You must be in a school year between 1 - 3", value = 1)
    @Max(message = "You must be in a school year between 1 - 3", value = 1)
    public int schoolYear;
    
    public LocalDate birthday;

    @NotBlank(message = "You must have a biography")
    public String biography;

    @NotNull(message = "You must have some photo")
    public String[] photos;
    
    @NotNull(message = "Favorite Subjects must not be null")
    public String[] favoriteSubjects;

    @NotNull(message = "CourseId must not be null")
    public Long courseId;

    @NotNull(message = "Contacts must not be null")
    public ContactsDTO contacts;

    public PreferencesDTO preferences;
}
