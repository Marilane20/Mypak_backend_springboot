package com.houseproject.MyPak.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.houseproject.MyPak.model.Property;
import com.houseproject.MyPak.model.PropertyStatut;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    
public List<Property> findByPropertyStatus(PropertyStatut status);
public List<Property> findByVilleAndPropertyStatus(String ville, PropertyStatut status);

public List<Property> findByQuartierAndPropertyStatus(String quartier, PropertyStatut status);

public List<Property> findByUserId(Long id);

public List <Property> findByPropertyStatus(PropertyStatut status, org.springframework.data.domain.Sort sort);
}
