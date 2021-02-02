package org.estudantinder.features.Users.UserEmailValidation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class DTO {

    @Email
    @NotBlank(message = "Email may not be blank")
    public String email;
    
}
