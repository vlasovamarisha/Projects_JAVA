package com.example.petshop.controller;

import com.example.petshop.model.Pet;
import com.example.petshop.service.ServiceLogic;
import com.example.petshop.exception.Exception;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ControllerTest {

    @InjectMocks
    private Controller controller;

    @Mock
    private ServiceLogic serviceLogic;

    private Pet pet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        pet = new Pet();
        pet.setId(1L);
        pet.setName("Jessy");
    }

    @Test
    public void testAddPet() {
        when(serviceLogic.addPet(any())).thenReturn(pet);

        ResponseEntity<Pet> response = controller.addPet(pet);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(pet, response.getBody());
    }

    @Test
    public void testUpdatePet() {
        when(serviceLogic.updatePet(any())).thenReturn(pet);

        ResponseEntity<Pet> response = controller.updatePet(pet);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(pet, response.getBody());
    }

    @Test
    public void testGetPetById() {
        when(serviceLogic.getPetById(anyLong())).thenReturn(pet);

        ResponseEntity<Pet> response = controller.getPetById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(pet, response.getBody());
    }

    @Test
    public void testDeletePet() {
        doNothing().when(serviceLogic).deletePet(anyLong());

        ResponseEntity<Void> response = controller.deletePet(1L);

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    public void testHandleNotFoundException() {
        Exception exception = new Exception("Pet not found");
        ResponseEntity<String> response = controller.handleNotFoundException(exception);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Pet not found", response.getBody());
    }
}
