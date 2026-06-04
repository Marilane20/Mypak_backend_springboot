package com.houseproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.houseproject.MyPak.model.AccountStatus;
import com.houseproject.MyPak.model.Role;
import com.houseproject.MyPak.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    //verifier si un email existe deja pour l'inscription
    boolean existsByEmail(String email);

    List<User>findByRoleAndStatus(Role role, AccountStatus status); 

}
