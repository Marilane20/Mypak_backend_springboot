package com.houseproject.MyPak.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequestDTO {

    @Email(message= "format email invalide")
    @NotBlank(message = "email est obligatoire")
    private String email;

    @NotBlank(message = "le mot de passe est obligatoire")
    @Size(min = 8, message ="le mot de passe doit avoir au moins 8 caracters")
    private String password;

    @NotBlank(message = "le nom est obligatoire")
    private String nom;

    private String prenom;

    @NotBlank(message = "le numero de telephone est obligatoire")
    private String telephone;

}
