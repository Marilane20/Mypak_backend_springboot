package com.houseproject.MyPak.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @Email(message = "format email invalide")
    @NotBlank
    private String email;

    @NotBlank(message = "le mot de passe est obligatoire")
    private String password;

}
