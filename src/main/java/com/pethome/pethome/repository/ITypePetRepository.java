package com.pethome.pethome.repository;

import java.util.Optional;

import com.pethome.pethome.models.TypePet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypePetRepository extends JpaRepository<TypePet, Long> {
    Optional<TypePet> findByName(String name);
    Boolean existsByName(String name);
}
