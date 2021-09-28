package org.estudantinder.users.domain.dtos.authentication;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AuthenticationRequestDTO {
    @NotBlank(message = "Nome não pode ser vazio")
    @Email(message = "Email deve ser um email valido")
    public String email;
    @NotBlank(message = "Nome não pode ser vazio")
    public String password;
}
