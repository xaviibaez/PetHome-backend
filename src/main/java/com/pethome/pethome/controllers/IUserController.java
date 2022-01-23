package com.pethome.pethome.controllers;

import java.util.List;
import java.util.Map;

import com.pethome.pethome.models.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IUserController {
    public List<User> getAllUsers();
    public ResponseEntity<User> getUserById(@PathVariable Long id);
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails);
    public User createUser(@RequestBody User User);
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id);
}
