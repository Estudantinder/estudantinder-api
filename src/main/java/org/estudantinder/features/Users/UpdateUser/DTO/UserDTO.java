package org.estudantinder.features.Users.UpdateUser.DTO;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

public class UserDTO {
    
    public String name;

    @Email
    public String email;

    public String password;
  
    @Max(message = "You must be in a school year between 1 - 3", value = 3)
    public int school_year;
    
    public LocalDate birth_date;

    @Size(message = "Your bio must have a maximum of 256 caracteres", max = 256)
    public String bio;

    public String gender;

    @Max(message = "Shift must be between 1(morning) and 2(evening)", value = 2)
    public int shift;

    public char classroom;

    @Size(message = "Your number of subjects must be between 1 and 3", min = 1, max = 3)
    public List<Long> subjects_ids;

    public Long course_id;

    public ContactsDTO contacts;
}
