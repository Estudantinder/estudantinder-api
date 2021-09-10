package org.estudantinder.features.Admins.CreateAdmin.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AdminDTO {
    
    @NotBlank(message = "Name may not be blank")
    public String name;

    @Email
    @NotBlank(message = "Email may not be blank")
    public String email;

    @NotBlank(message = "Password may not be blank")
    @Size(message = "Your Password must have at least 8 characters", min = 8)
    public String password;
  
}
