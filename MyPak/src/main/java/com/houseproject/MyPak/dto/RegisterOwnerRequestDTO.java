package com.houseproject.MyPak.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterOwnerRequestDTO {

    @Email(message= "format email invalide")
    @NotBlank(message = "Email obligatoire")
    private String email;

    @NotBlank(message = "le mot de passe est obligatoire")
    @Size(min = 8, message = "le mot de passe doit avoir au moins 8 caractères")
    private String password;

    @NotBlank(message = "nom obligatoire")
    private String nom;

    private String prenom;

    @NotBlank
    private String telephone;

    @NotBlank(message = "le numero de cni est obligatoire")
    private String numeroCni;

}
