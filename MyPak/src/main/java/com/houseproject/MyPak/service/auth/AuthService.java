package com.houseproject.MyPak.service.auth;

import com.houseproject.MyPak.dto.AuthResponseDTO;
import com.houseproject.MyPak.dto.LoginRequestDTO;
import com.houseproject.MyPak.dto.RegisterOwnerRequestDTO;
import com.houseproject.MyPak.dto.RegisterUserRequestDTO;

public interface AuthService {

    //inscription d'un simple utilisateur

    AuthResponseDTO registerUser(RegisterUserRequestDTO dto);

    //inscription d'un bailleur

    AuthResponseDTO registerOwner(RegisterOwnerRequestDTO dto);

    //connexion pour tout le monde 

    AuthResponseDTO login (LoginRequestDTO dto);

}
