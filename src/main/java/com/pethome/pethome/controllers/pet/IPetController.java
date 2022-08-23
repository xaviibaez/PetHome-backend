package com.pethome.pethome.controllers.pet;

import java.util.Map;

import com.pethome.pethome.models.Pet;
import com.pethome.pethome.payload.request.PetRequest;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IPetController {
    public CollectionModel<EntityModel<Pet>> getAllPets();
    public EntityModel<Pet> getPetById(@PathVariable Long id);
    public ResponseEntity<Pet> getPetByName(@PathVariable String name);
    public ResponseEntity<EntityModel<Pet>> updatePet(@PathVariable Long id, @RequestBody PetRequest petRequest);
    public ResponseEntity<?> createPet(@RequestBody PetRequest petRequest);
    public ResponseEntity<Map<String, Boolean>> deletePet(@PathVariable Long id);
}
