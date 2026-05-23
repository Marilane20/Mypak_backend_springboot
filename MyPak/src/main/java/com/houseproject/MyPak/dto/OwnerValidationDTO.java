package com.houseproject.MyPak.dto;

import com.houseproject.MyPak.model.AccountStatus;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OwnerValidationDTO {

    @NotNull(message = "l'id du proprietaire est obligatoire"   )
    private Long ownerId;

    @NotNull(message = "le status de validation est obligatoire")
    private AccountStatus status;

    private String commentaire ;// optionnel pour fournir des details sur la validation ou le refus

}
