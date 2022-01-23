package com.pethome.pethome.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pethome.pethome.models.User;
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
public class UserController implements IUserController{

    @Autowired
	private UserRepository userRepository;
	// get all users
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}		
	
	// create user rest api
	@PostMapping("/users")
	@PreAuthorize("hasRole('ROLE_MODERATOR')")
	public User createUser(@RequestBody User user) {
		if(!userRepository.existsById(user.getId())){
			return userRepository.save(user);
		}
		else{
			throw new ResourceDuplicateException("User alredy exist with id :" + user.getId());
		}
	}
	
	// get user by id rest api
	@GetMapping("/users/{id}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		User user = userRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
        
		return ResponseEntity.ok(user);
	}
	
	// update user rest api
	@PutMapping("/users/{id}")
	@PreAuthorize("hasRole('ROLE_MODERATOR')")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails){
		User user = userRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
		
		user.setEmail(userDetails.getEmail());
		
		User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	// delete user rest api
	@DeleteMapping("/users/{id}")
	@PreAuthorize("hasRole('ROLE_MODERATOR')")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id){
		User user = userRepository.findById(id).
            orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
		
		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
