package com.example.petshop.repository;

import com.example.petshop.model.Pet;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class Collection {
    private final Map<Long, Pet> pets = new HashMap<>();
    private long nextId = 1;

    public List<Pet> findAll() {
        return new ArrayList<>(pets.values());
    }

    public Optional<Pet> findById(Long id) {
        return Optional.ofNullable(pets.get(id));
    }

    public Pet save(Pet pet) {
        if (pet.getId() == null) {
            pet.setId(nextId++);
        }
        pets.put(pet.getId(), pet);
        return pet;
    }

    public void deleteById(Long id) {
        pets.remove(id);
    }
}