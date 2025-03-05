package com.example.petshop.controller;

import com.example.petshop.model.Pet;
import com.example.petshop.service.ServiceLogic;
import org.springframework.http.HttpStatus;
import com.example.petshop.exception.Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/petshop")
public class Controller {
    private final ServiceLogic serviceLogic;

    @Autowired
    public Controller(ServiceLogic serviceLogic) {
        this.serviceLogic = serviceLogic;
    }

    @PostMapping
    public ResponseEntity<Pet> addPet(@RequestBody Pet pet) {
        return ResponseEntity.ok(serviceLogic.addPet(pet));
    }

    @PutMapping
    public ResponseEntity<Pet> updatePet(@RequestBody Pet pet) {
        return ResponseEntity.ok(serviceLogic.updatePet(pet));
    }

    @GetMapping("/{petId}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long petId) {
        return ResponseEntity.ok(serviceLogic.getPetById(petId));
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> deletePet(@PathVariable Long petId) {
        serviceLogic.deletePet(petId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(Exception ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
