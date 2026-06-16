package com.houseproject.MyPak.controller;

import com.houseproject.MyPak.dto.AuthResponseDTO;
import com.houseproject.MyPak.dto.LoginRequestDTO;
import com.houseproject.MyPak.dto.RegisterOwnerRequestDTO;
import com.houseproject.MyPak.dto.RegisterUserRequestDTO;
import com.houseproject.MyPak.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // Inscription utilisateur simple
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(
            @Valid @RequestBody RegisterUserRequestDTO dto) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(authService.registerUser(dto));
    }

    // Inscription bailleur
    @PostMapping("/register-owner")
    public ResponseEntity<AuthResponseDTO> registerOwner(
            @Valid @RequestBody RegisterOwnerRequestDTO dto) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(authService.registerOwner(dto));
    }

    // Connexion
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
}