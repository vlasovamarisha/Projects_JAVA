package com.example.petshop.service;

import com.example.petshop.exception.Exception;
import com.example.petshop.model.Pet;
import com.example.petshop.repository.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceLogic {
    private final Collection collection;

    @Autowired
    public ServiceLogic(Collection collection) {
        this.collection = collection;
    }

    public Pet addPet(Pet pet) {
        return collection.save(pet);
    }

    public Pet updatePet(Pet pet) {
        if (pet.getId() == null || collection.findById(pet.getId()).isEmpty()) {
            throw new Exception("Pet not found");
        }
        return collection.save(pet);
    }

    public Pet getPetById(Long id) {
        return collection.findById(id)
                .orElseThrow(() -> new Exception("Pet not found"));
    }

    public void deletePet(Long id) {
        if (collection.findById(id).isEmpty()) {
            throw new Exception("Pet not found");
        }
        collection.deleteById(id);
    }
}