package com.example.profile_service.controller;

import com.example.profile_service.model.Pet;
import com.example.profile_service.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    @PostMapping
    public ResponseEntity<Pet> registerPet(@RequestBody Pet pet) {
        return ResponseEntity.ok(petService.registerPet(pet));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPet(@PathVariable String id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Pet>> getPetsByOwner(@PathVariable String ownerId) {
        return ResponseEntity.ok(petService.getPetsByOwner(ownerId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable String id, @RequestBody Pet petDetails) {
        return ResponseEntity.ok(petService.updatePet(id, petDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable String id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/chip/{chipNumber}")
    public ResponseEntity<Pet> getPetByChipNumber(@PathVariable String chipNumber) {
        return ResponseEntity.ok(petService.getPetByChipNumber(chipNumber));
    }

    @GetMapping
    public ResponseEntity<List<Pet>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }
}