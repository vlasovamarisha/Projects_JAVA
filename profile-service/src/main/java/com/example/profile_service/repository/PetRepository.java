package com.example.profile_service.repository;

import com.example.profile_service.model.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface PetRepository extends MongoRepository<Pet, String> {
    List<Pet> findByOwnerId(String ownerId);
    Optional<Pet> findByChipNumber(String chipNumber); // Изменено на Optional
    List<Pet> findBySpecies(String species);
}
