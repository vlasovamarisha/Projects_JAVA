package com.example.profile_service.service;

import com.example.profile_service.model.Pet;
import com.example.profile_service.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;

    public Pet registerPet(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet getPetById(String id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
    }

    public List<Pet> getPetsByOwner(String ownerId) {
        return petRepository.findByOwnerId(ownerId);
    }

    public Pet updatePet(String id, Pet petDetails) {
        Pet pet = getPetById(id);
        pet.setName(petDetails.getName());
        pet.setSpecies(petDetails.getSpecies());
        pet.setBreed(petDetails.getBreed());
        pet.setBirthDate(petDetails.getBirthDate());
        pet.setChipNumber(petDetails.getChipNumber());
        return petRepository.save(pet);
    }

    public void deletePet(String id) {
        petRepository.deleteById(id);
    }

    public Pet getPetByChipNumber(String chipNumber) {
        return petRepository.findByChipNumber(chipNumber)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }
}