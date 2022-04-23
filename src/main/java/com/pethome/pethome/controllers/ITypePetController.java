package com.pethome.pethome.controllers;

import java.util.List;
import java.util.Map;

import com.pethome.pethome.models.TypePet;
import com.pethome.pethome.payload.response.MessageResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ITypePetController {
    public ResponseEntity<List<TypePet>> getAllTypePets();
    public ResponseEntity<TypePet> getTypePetById(@PathVariable Long id);
    public ResponseEntity<TypePet> updateTypePet(@PathVariable Long id, @RequestBody TypePet type);
    public ResponseEntity<MessageResponse> createTypePet(@RequestBody TypePet typePet);
    public ResponseEntity<Map<String, Boolean>> deleteTypePet(@PathVariable Long id);
}
