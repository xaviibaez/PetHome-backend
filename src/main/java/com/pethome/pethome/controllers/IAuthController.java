package com.pethome.pethome.controllers;

import javax.validation.Valid;

import com.pethome.pethome.payload.request.LoginRequest;
import com.pethome.pethome.payload.request.SignupRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IAuthController {
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest);
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest);
}
