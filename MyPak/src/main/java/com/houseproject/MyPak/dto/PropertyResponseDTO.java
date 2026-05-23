package com.houseproject.MyPak.dto;
import java.time.LocalDateTime;
import java.util.List;
import com.houseproject.MyPak.model.PropertyStatut;
import com.houseproject.MyPak.model.PropertyType;

import lombok.Data;

@Data
public class PropertyResponseDTO {

    private Long id;
    private String titre;
    private String description;
    private Double prix;
    private String ville;
    private String quartier;
    private String adresse;
    private PropertyType type;
    private Integer nombrePieces;
    private Double superficie;
    private PropertyStatut propertyStatus;

    //infos bailleur - seulement ce dont l'utilisateur a besoin pour contacter le bailleur
    private String nomBailleur;
    private String prenomBailleur;
    private String telephoneBailleur;

    private List<String> photos; // URLs des photos de la propriété
    private LocalDateTime createdAt;
}
