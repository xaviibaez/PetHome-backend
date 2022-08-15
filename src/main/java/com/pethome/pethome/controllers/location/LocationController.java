package com.pethome.pethome.controllers.location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pethome.pethome.models.Location;
import com.pethome.pethome.models.User;
import com.pethome.pethome.payload.response.MessageResponse;
import com.pethome.pethome.repository.ILocationRepository;
import com.pethome.pethome.repository.IUserRepository;

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
import com.pethome.pethome.Utils.Utils;

import com.pethome.pethome.exception.ResourceNotFoundException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/")
public class LocationController {
    @Autowired
	private ILocationRepository locationRepository;

    @Autowired
	private IUserRepository userRepository;

    // get all locations
	@GetMapping("/locations")
	public ResponseEntity<List<Location>> getAllLocations(){
		return ResponseEntity.ok(locationRepository.findAll());
	}

	@GetMapping("/locations/{id}")
	public ResponseEntity<Location> getlocationById(Long id) {
		Location location = locationRepository.findById(id).
			orElseThrow(() -> new ResourceNotFoundException("Location not exist with id :" + id));

		return ResponseEntity.ok(location);
	}
	
	// create location
	@PostMapping("/locations")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<MessageResponse> createLocation(@RequestBody Location locationDetails) {
        locationRepository.save(locationDetails);

        Long principalId = Utils.userLogged();
		User user = userRepository.findById(principalId).
			orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + principalId));

		user.getLocations().add(locationDetails);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("Location registered successfully!")); 
	}
	
	// update location
	@PutMapping("/locations/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location locationDetails){
		Location location = locationRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Location not exist with id :" + id));

        location.setDescription(locationDetails.getDescription());
        location.setName(locationDetails.getName());
		
		return ResponseEntity.ok(locationRepository.save(location));
	}
	
	// delete location
	@DeleteMapping("/locations/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<Map<String, Boolean>> deleteLocation(@PathVariable Long id){
		Location location = locationRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("Location not exist with id :" + id));
		
		locationRepository.delete(location);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
