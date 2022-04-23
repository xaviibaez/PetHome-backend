package com.pethome.pethome.repository;

import java.util.Optional;

import com.pethome.pethome.models.Pet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPetRepository extends JpaRepository<Pet, Long> {
    Optional<Pet> findByName(String username);
    Boolean existsByName(String username);
}
