package com.pethome.pethome.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.pethome.pethome.models.Pet;
import com.pethome.pethome.models.TypePet;
import com.pethome.pethome.models.User;
import com.pethome.pethome.payload.Pet.PetRequest;
import com.pethome.pethome.repository.IPetRepository;
import com.pethome.pethome.repository.ITypePetRepository;
import com.pethome.pethome.repository.IUserRepository;
import com.pethome.pethome.Utils.Utils;
import com.pethome.pethome.assembler.PetModelAssembler;
import com.pethome.pethome.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/")
public class PetController implements IPetController{

    @Autowired
	private IPetRepository petRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ITypePetRepository typePetRepository;

	@Autowired
	private PetModelAssembler petModelAssembler;

	@GetMapping("/pets")
	public CollectionModel<EntityModel<Pet>> getAllPets(){

	List<EntityModel<Pet>> pets = petRepository.findAll().stream()
		.map(petModelAssembler::toModel) //
		.collect(Collectors.toList());
   
	 return CollectionModel.of(pets, linkTo(methodOn(PetController.class).getAllPets()).withSelfRel());
	}

	@GetMapping("/pets/{id}")
	public EntityModel<Pet> getPetById(@PathVariable Long id) {
		Pet pet = petRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("Pet not exist with id :" + id));
    	
		return petModelAssembler.toModel(pet);
	}

	// get pet by name
	@GetMapping("/pets/name/{name}")
	public ResponseEntity<Pet> getPetByName(@PathVariable String name) {
		Pet pet = petRepository.findByName(name).
            orElseThrow(() -> new ResourceNotFoundException("Pet not exist with name :" + name));
    	
		return ResponseEntity.ok(pet);
	}

	// get all pets per user
	@GetMapping("/pets/user/{id}")
	public ResponseEntity<List<Pet>> getAllPetsByUsers(@PathVariable Long id){
		User user = userRepository.findById(id).
			orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
			
		return ResponseEntity.ok(user.getPets());
	}
	
	// create pet
	@PostMapping("/pets")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<?> createPet(@RequestBody PetRequest petRequest) {
		TypePet typePet = typePetRepository.findById(Long.valueOf(petRequest.getTypePet()))
			.orElseThrow(() -> new ResourceNotFoundException("Type pet not exist with id :" + petRequest.getTypePet()));

		Pet pet = new Pet();
		pet.setName(petRequest.getName());
		pet.setDescription(petRequest.getDescription());
		pet.setBehaviour(petRequest.getBehaviour());
		pet.setAge(petRequest.getAge());
		pet.setSterilized(petRequest.getSterilized());
		pet.setAdopted(petRequest.getAdopted());
		pet.setUrgentAdoption(petRequest.getUrgentAdoption());
		pet.setSex(petRequest.getSex());
		pet.setTypePet(typePet);

		EntityModel<Pet> entityModel = petModelAssembler.toModel(petRepository.save(pet));

		Long principalId = Utils.userLogged();
		User user = userRepository.findById(principalId).
			orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + principalId));

		user.getPets().add(pet);
		userRepository.save(user);

		return ResponseEntity
			.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
      		.body(entityModel);
	}
	
	// update pet
	@PutMapping("/pets/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody PetRequest petRequest){
		Pet pet = petRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("Pet not exist with id :" + id));
		
		TypePet typePet = typePetRepository.findById(Long.valueOf(petRequest.getTypePet()))
			.orElseThrow(() -> new ResourceNotFoundException("TypePet not exist with id :" + petRequest.getTypePet()));

		pet.setName(petRequest.getName());
		pet.setDescription(petRequest.getDescription());
		pet.setBehaviour(petRequest.getBehaviour());
		pet.setAge(petRequest.getAge());
		pet.setSterilized(petRequest.getSterilized());
		pet.setAdopted(petRequest.getAdopted());
		pet.setUrgentAdoption(petRequest.getUrgentAdoption());
		pet.setSex(petRequest.getSex());
		pet.setTypePet(typePet);

		return ResponseEntity.ok(petRepository.save(pet));
	}
	
	// delete pet
	@DeleteMapping("/pets/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<Map<String, Boolean>> deletePet(@PathVariable Long id){
		Pet pet = petRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("Pet not exist with id :" + id));
			
		Long principalId = Utils.userLogged();
		User user = userRepository.findById(principalId).
				orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + principalId));

		user.getPets().remove(pet);
		userRepository.save(user);

		petRepository.delete(pet);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted " + "ID: " + pet.getId() + " Name: " + pet.getName(), 
					Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

