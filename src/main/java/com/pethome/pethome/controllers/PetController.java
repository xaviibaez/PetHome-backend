package com.pethome.pethome.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pethome.pethome.models.Pet;
import com.pethome.pethome.models.User;
import com.pethome.pethome.repository.PetRepository;
import com.pethome.pethome.repository.UserRepository;
import com.pethome.pethome.exception.ResourceNotFoundException;
import com.pethome.pethome.exception.ResourceDuplicateException;

import org.springframework.beans.factory.annotation.Autowired;
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
	private PetRepository petRepository;

	@Autowired
	private UserRepository userRepository;

	// get all pets
	@GetMapping("/pets")
	public List<Pet> getAllPets(){
		return petRepository.findAll();
	}	
	
	// get all users
	@GetMapping("/petsByUser/{id}")
	public List<Pet> getAllPetsByUsers(@PathVariable Long id){
		User user = userRepository.findById(id).
			orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
		return user.getPets();
	}		
	
	// create pet rest api
	@PostMapping("/pets")
	@PreAuthorize("hasRole('ROLE_MODERATOR')")
	public Pet createPet(@RequestBody Pet pet) {
		if(!petRepository.existsById(pet.getId())){
			return petRepository.save(pet);
		}
		else{
			throw new ResourceDuplicateException("Pet alredy exist with id :" + pet.getId());
		}
	}
	
	// get pet by id rest api
	@GetMapping("/pets/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
		Pet pet = petRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("Pet not exist with id :" + id));
        
		return ResponseEntity.ok(pet);
	}
	
	// update pet rest api
	@PutMapping("/pets/{id}")
	@PreAuthorize("hasRole('ROLE_MODERATOR')")
	public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody Pet petDetails){
		Pet pet = petRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("Pet not exist with id :" + id));
		
		pet.setName(petDetails.getName());
		
		Pet updatedPet = petRepository.save(pet);
		return ResponseEntity.ok(updatedPet);
	}
	
	// delete pet rest api
	@DeleteMapping("/pets/{id}")
	@PreAuthorize("hasRole('ROLE_MODERATOR')")
	public ResponseEntity<Map<String, Boolean>> deletePet(@PathVariable Long id){
		Pet pet = petRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("Pet not exist with id :" + id));
		
		petRepository.delete(pet);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}

