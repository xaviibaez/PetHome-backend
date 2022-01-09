package com.pethome.pethome.controllers;

import java.util.List;
import java.util.Map;

import com.pethome.pethome.models.Pet;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IPetController {
    public List<Pet> getAllPets();
    public ResponseEntity<Pet> getPetById(@PathVariable Long id);
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody Pet petDetails);
    public Pet createPet(@RequestBody Pet pet);
    public ResponseEntity<Map<String, Boolean>> deletePet(@PathVariable Long id);
}
