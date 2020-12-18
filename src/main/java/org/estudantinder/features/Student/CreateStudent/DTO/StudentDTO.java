package org.estudantinder.features.Student.CreateStudent.DTO;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StudentDTO {
    
    @NotBlank(message = "Name may not be blank")
    public String name;

    @Email
    @NotBlank(message = "Email may not be blank")
    public String email;

    @NotBlank(message = "Password may not be blank")
    @Size(message = "Your Password must have at least 8 characters", min = 8)
    public String password;
  
    @NotNull(message = "School Year must not be null")
    @Min(message = "You must be in a school year between 1 - 3", value = 1)
    @Max(message = "You must be in a school year between 1 - 3", value = 3)
    public int school_year;
    
    @NotNull
    public LocalDate birth_date;

    @NotBlank(message = "You must have a biography")
    @Size(message = "Your bio must have a maximum of 256 caracteres", max = 256)
    public String bio;

    public String gender;

    @NotNull
    @Min(message = "Shift must be between 1(morning) and 2(evening)", value = 1)
    @Max(message = "Shift must be between 1(morning) and 2(evening)", value = 2)
    public int shift;

    @NotNull
    public char classroom;

    @NotNull(message = "You must have some photo")
    @Size(message = "Your number of photos must be between 1 and 6", min = 1, max = 6)
    public String[] photos;
    
    @NotNull(message = "Favorite Subjects must not be null")
    @Size(message = "Your number of subjects must be between 1 and 3", min = 1, max = 3)
    public String[] subjects;

    @NotNull(message = "CourseId must not be null")
    public Long course_id;

    @NotNull(message = "Contacts must not be null")
    public ContactsDTO contacts;

    @Valid
    public PreferencesDTO preferences;
}
