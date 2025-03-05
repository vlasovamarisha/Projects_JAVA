package com.example.petshop.service;

import com.example.petshop.exception.Exception;
import com.example.petshop.model.Pet;
import com.example.petshop.repository.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ServiceLogicTest {

    @InjectMocks
    private ServiceLogic serviceLogic;

    @Mock
    private Collection collection;

    private Pet pet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pet = new Pet();
        pet.setId(1L);
        pet.setName("Jessy");
    }

    @Test
    public void addPet_ShouldReturnSavedPet() {
        when(collection.save(any(Pet.class))).thenReturn(pet);

        assertEquals(pet, serviceLogic.addPet(pet));
    }

    @Test
    public void updatePet_ShouldReturnUpdatedPet_WhenPetExists() {
        when(collection.findById(1L)).thenReturn(Optional.of(pet));
        when(collection.save(any(Pet.class))).thenReturn(pet);

        assertEquals(pet, serviceLogic.updatePet(pet));
    }

    @Test
    public void updatePet_ShouldThrowException_WhenPetNotFound() {
        when(collection.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> serviceLogic.updatePet(pet));

        assertEquals("Pet not found", exception.getMessage());
    }

    @Test
    public void getPetById_ShouldReturnPet_WhenExists() {
        when(collection.findById(1L)).thenReturn(Optional.of(pet));

        assertEquals(pet, serviceLogic.getPetById(1L));
    }

    @Test
    public void getPetById_ShouldThrowException_WhenNotFound() {
        when(collection.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> serviceLogic.getPetById(1L));

        assertEquals("Pet not found", exception.getMessage());
    }

    @Test
    public void deletePet_ShouldDelete_WhenExists() {
        when(collection.findById(1L)).thenReturn(Optional.of(pet));

        serviceLogic.deletePet(1L);

        verify(collection).deleteById(1L);
    }

    @Test
    public void deletePet_ShouldThrowException_WhenNotFound() {
        when(collection.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> serviceLogic.deletePet(1L));

        assertEquals("Pet not found", exception.getMessage());
    }
}
