package com.houseproject.MyPak.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.houseproject.MyPak.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long>{

    List<Photo> findByPropertyId(Long propertyId);

    // Supprimer toutes les photos d'un logement
    void deleteByPropertyId(Long propertyId);

    
}
