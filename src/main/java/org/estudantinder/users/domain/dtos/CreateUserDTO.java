package org.estudantinder.users.domain.dtos;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.smallrye.common.constraint.NotNull;

public class CreateUserDTO {

    @NotBlank(message = "Nome não pode ser vazio")
    public String name;

    @NotBlank(message = "Senha não pode ser vazia")
    @Size(min = 8, message = "Senha deve conter ao menos 8 caracteres")
    @Pattern(regexp = ".*\\d.*", message = "Senha deve conter ao menos um numero")
    public String password;

    @NotBlank(message = "Email não pode ser vazio")
    @Email(message = "Email deve ser um email valido")
    public String email;

    @NotNull
    @Past(message = "Data de nascimento deve ser no passado")
    public Date birth_date;

    @NotBlank(message = "Biografia não pode ser vazia")
    public String bio;

    public String gender;

    @Min(value = 1, message = "Sua serie deve estar entre 1 e 3")
    @Max(value = 3, message = "Sua serie deve estar entre 1 e 3")
    public int school_year;

    public int shift;

    @NotNull
    @Size(max = 1, message = "Classe só pode conter uma letra")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Classe deve deve ser uma letra")
    public String classroom;

    @NotNull
    public UUID course_id;
    
    @NotNull
    public CreateContactsDTO contacts;

    public CreatePreferencesDTO preferences;

}
