package com.houseproject.MyPak.service.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.houseproject.MyPak.dto.AuthResponseDTO;
import com.houseproject.MyPak.dto.LoginRequestDTO;
import com.houseproject.MyPak.dto.RegisterOwnerRequestDTO;
import com.houseproject.MyPak.dto.RegisterUserRequestDTO;
import com.houseproject.MyPak.exceptions.AccountNotApprovedException;
import com.houseproject.MyPak.exceptions.EmailAlreadyExistsException;
import com.houseproject.MyPak.exceptions.RessourceNotFoundException;
import com.houseproject.MyPak.model.AccountStatus;
import com.houseproject.MyPak.model.Role;
import com.houseproject.MyPak.model.User;
import com.houseproject.MyPak.repository.UserRepository;
import com.houseproject.MyPak.service.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    public AuthResponseDTO registerUser(RegisterUserRequestDTO dto) {
        //on verifie que l'email n'existe pas deja
        if(userRepository.existsByEmail(dto.getEmail())){
            throw new EmailAlreadyExistsException(
                "un compte existe deja avec l'email:" + dto.getEmail()

            );
        }
       //construire l'objet

        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // mot de passe hashé
        user.setNom(dto.getNom());
        user.setPrenom(dto.getPrenom());
        user.setTelephone(dto.getTelephone());
        user.setRole(Role.ROLE_USER);
        user.setStatus(AccountStatus.APPROVED);// utilisateur simple est approuve directement

        userRepository.save(user);
             UserDetails userDetails = userDetailsService
             .loadUserByUsername(user.getEmail());

                String token = jwtService.generateToken(userDetails,
                 user.getRole().name());

        return new AuthResponseDTO(
            token,
            user.getEmail(),
            user.getNom(),
            user.getRole().name(),
            user.getStatus().name()
        );
            
        
       
    }

    

    @Override
    public AuthResponseDTO registerOwner(RegisterOwnerRequestDTO dto) {
       

        // 1. Vérifier que l'email n'existe pas déjà
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException(
                "Un compte existe déjà avec l'email : " + dto.getEmail()
            );
        }

        // 2. Construire l'objet User bailleur
        User owner = new User();
        owner.setEmail(dto.getEmail());
        owner.setPassword(passwordEncoder.encode(dto.getPassword()));
        owner.setNom(dto.getNom());
        owner.setPrenom(dto.getPrenom());
        owner.setTelephone(dto.getTelephone());
        owner.setNumeroCni(dto.getNumeroCni());
        owner.setRole(Role.ROLE_OWNER);
        owner.setStatus(AccountStatus.PENDING); // bailleur en attente de validation par l' admin

        // 3. Sauvegarder en base
        userRepository.save(owner);
        UserDetails userDetails = userDetailsService
            .loadUserByUsername(owner.getEmail());

        String token = jwtService.generateToken(userDetails,
             owner.getRole().name());

        // 4. Retourner la réponse
        return new AuthResponseDTO(
            token, // pas de token — compte pas encore approuvé
            owner.getEmail(),
            owner.getNom(),
            owner.getRole().name(),
            owner.getStatus().name()
        );
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO dto) {
        // 1. Chercher l'utilisateur par email
        User user = userRepository.findByEmail(dto.getEmail())
            .orElseThrow(() -> new RessourceNotFoundException(
                "Aucun compte trouvé avec l'email : " + dto.getEmail()
            ));

        // 2. Vérifier le mot de passe
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RessourceNotFoundException("Email ou mot de passe incorrect");
        }

        // 3. Vérifier que le compte est approuvé
        if (user.getStatus() == AccountStatus.PENDING) {
            throw new AccountNotApprovedException(
                "Votre compte est en attente de validation par l'administrateur"
            );
        }

        if (user.getStatus() == AccountStatus.BANNED) {
            throw new AccountNotApprovedException(
                "Votre compte a été suspendu. Contactez l'administrateur"
            );
        }

        UserDetails userDetails = userDetailsService
            .loadUserByUsername(user.getEmail());

            String token = jwtService.generateToken(userDetails,
            user.getRole().name());

        // 4. Retourner la réponse avec token provisoire
        return new AuthResponseDTO(
            token,
            user.getEmail(),
            user.getNom(),
            user.getRole().name(),
            user.getStatus().name()
        );
      

    }
}
