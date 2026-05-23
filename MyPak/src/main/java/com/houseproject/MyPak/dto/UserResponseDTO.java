package com.houseproject.MyPak.dto;

import java.time.LocalDateTime;

import com.houseproject.MyPak.model.AccountStatus;
import com.houseproject.MyPak.model.Role;

import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;
    private String email;
    private String nom;
    private String telephone;
    private Role role;
    private AccountStatus status;
    private LocalDateTime createdAt;
}
