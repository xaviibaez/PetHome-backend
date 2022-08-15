package com.pethome.pethome.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.pethome.pethome.controllers.pet.PetController;
import com.pethome.pethome.models.Pet;

@Component
public class PetModelAssembler implements RepresentationModelAssembler<Pet, EntityModel<Pet>> {

    @Override
    public EntityModel<Pet> toModel(Pet pet) {
        return EntityModel.of(pet, //
            linkTo(methodOn(PetController.class).getPetById(pet.getId())).withSelfRel(),
            linkTo(methodOn(PetController.class).getAllPets()).withRel("pets"));
    }
}

