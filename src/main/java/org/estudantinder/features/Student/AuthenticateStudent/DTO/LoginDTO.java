package org.estudantinder.features.Student.AuthenticateStudent.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginDTO {
    
    @NotBlank(message = "Email may not be blank")
    @Email
    public String email;

    @NotBlank(message = "Password may not be blank")
    public String password;
    
}
