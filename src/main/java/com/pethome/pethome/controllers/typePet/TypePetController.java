package com.pethome.pethome.controllers.typePet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pethome.pethome.models.TypePet;
import com.pethome.pethome.payload.response.MessageResponse;
import com.pethome.pethome.repository.ITypePetRepository;

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

import com.pethome.pethome.exception.ResourceDuplicateException;
import com.pethome.pethome.exception.ResourceNotFoundException;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/")
public class TypePetController implements ITypePetController{

	@Autowired
	private ITypePetRepository typePetRepository;
	
	// get all type pets
	@GetMapping("/typePets")
	public ResponseEntity<List<TypePet>> getAllTypePets(){
		return ResponseEntity.ok(typePetRepository.findAll());
	}

	@Override
	@GetMapping("/typePets/{id}")
	public ResponseEntity<TypePet> getTypePetById(Long id) {
		TypePet typePet = typePetRepository.findById(id).
			orElseThrow(() -> new ResourceNotFoundException("Type pet not exist with id :" + id));

		return ResponseEntity.ok(typePet);
	}
	
	// create type pet
	@PostMapping("/typePets")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<MessageResponse> createTypePet(@RequestBody TypePet typePetDetails) {

		TypePet typePet = typePetRepository.findByName(typePetDetails.getName()).
            orElseThrow(() -> new ResourceDuplicateException("Type pet exist with name :" + typePetDetails.getName()));

        typePetRepository.save(typePet);
		return ResponseEntity.ok(new MessageResponse("Type pet registered successfully!")); 
	}
	
	// update type pet
	@PutMapping("/typePets/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<TypePet> updateTypePet(@PathVariable Long id, @RequestBody TypePet typePetDetails){
		TypePet typePet = typePetRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("TypePet not exist with id :" + id));

        typePet.setName(typePetDetails.getName());
		
		return ResponseEntity.ok(typePetRepository.save(typePet));
	}
	
	// delete type pet
	@DeleteMapping("/typePets/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<Map<String, Boolean>> deleteTypePet(@PathVariable Long id){
		TypePet typePet = typePetRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("Type pet not exist with id :" + id));
		
		typePetRepository.delete(typePet);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
