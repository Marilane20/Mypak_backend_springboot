package com.houseproject.MyPak.dto;

import org.springframework.security.core.parameters.P;

import com.houseproject.MyPak.model.PropertyType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PropertyRequestDTO {

    @NotBlank(message = "le titre est obligatoire")
    private String title;

    private String description;

    @NotNull(message = "le prix est obligatoire")
    @Positive(message = "le prix doit être positif")
    private Double prix;

    @NotBlank(message = "ville obligatoire")
    private String ville;

    @NotBlank(message = "le quartier est obligatoire")
    private String quartier;

    private String adresse;

    @NotNull(message = "Type de logement obligatoire")
    private PropertyType type;

    private Integer nombrePieces;

    @Positive(message = "la superficie doit etre positive")

    private Double superficie;
    

}
