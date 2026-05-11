package com.houseproject.MyPak.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "property")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String titre;
    private String description;
    private Double prix;
    private String ville;
    private String quartier;
    private boolean isAvailable = true;
    private String adresse; 
    @Enumerated(EnumType.STRING)
    private PropertyType type;
    private Integer nombrePieces;
    private Double superficies;
    private LocalDateTime createdAt;

    @PrePersist
    protected void Oncrate() {
        this.createdAt = LocalDateTime.now();
    }
;

    @ManyToOne
    @JoinColumn(name = "user_id") // Creer une colonne user_id pour la cle etrangere
    private User user;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<Photo> photos;

    


}
