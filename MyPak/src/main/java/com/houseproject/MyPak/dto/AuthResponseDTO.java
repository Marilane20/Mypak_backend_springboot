package com.houseproject.MyPak.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDTO {

    private String token;
    private String email;
    private String nom;
    private String role;
    private String status;

}
