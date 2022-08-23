package com.pethome.pethome.controllers.address;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pethome.pethome.models.Address;
import com.pethome.pethome.models.Location;
import com.pethome.pethome.payload.request.AddressRequest;
import com.pethome.pethome.payload.response.MessageResponse;
import com.pethome.pethome.repository.IAddressRepository;
import com.pethome.pethome.repository.ILocationRepository;

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

import com.pethome.pethome.exception.ResourceNotFoundException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/")
public class AddressController {
    @Autowired
	private IAddressRepository addressRepository;

    @Autowired
	private ILocationRepository locationRepository;

    // get all address
	@GetMapping("/address")
	public ResponseEntity<List<Address>> getAllAddresss(){
		return ResponseEntity.ok(addressRepository.findAll());
	}

	@GetMapping("/address/{id}")
	public ResponseEntity<Address> getaddressById(Long id) {
		Address address = addressRepository.findById(id).
			orElseThrow(() -> new ResourceNotFoundException("Address not exist with id :" + id));

		return ResponseEntity.ok(address);
	}
	
	// create address
	@PostMapping("/address")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<MessageResponse> createAddress(@RequestBody AddressRequest addressDetails) {
        Address address = new Address();
        address.setDescription(addressDetails.getDescription());
        address.setName(addressDetails.getName());
        addressRepository.save(address);

        Location location = locationRepository.getById(addressDetails.getLocationId());
        location.setAddress(address);
        locationRepository.save(location);

		return ResponseEntity.ok(new MessageResponse("Address registered successfully!")); 
	}
	
	// update address
	@PutMapping("/address/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address addressDetails){
		Address address = addressRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Address not exist with id :" + id));

        address.setDescription(addressDetails.getDescription());
        address.setName(addressDetails.getName());
		
		return ResponseEntity.ok(addressRepository.save(address));
	}
	
	// delete address
	@DeleteMapping("/address/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<Map<String, Boolean>> deleteAddress(@PathVariable Long id){
		Address address = addressRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("Address not exist with id :" + id));
		
		addressRepository.delete(address);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
