package com.example.profile_service.controller;

import com.example.profile_service.model.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class PetControllerIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegisterAndGetPet() throws Exception {
        Pet pet = new Pet();
        pet.setName("Buddy");
        pet.setSpecies("Dog");
        pet.setBreed("Golden Retriever");
        pet.setOwnerId("owner123");

        // Create pet
        String json = objectMapper.writeValueAsString(pet);

        String response = mockMvc.perform(post("/api/pets")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Buddy"))
                .andReturn().getResponse().getContentAsString();

        Pet createdPet = objectMapper.readValue(response, Pet.class);

        // Get pet
        mockMvc.perform(get("/api/pets/{id}", createdPet.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdPet.getId()))
                .andExpect(jsonPath("$.name").value("Buddy"));
    }

    @Test
    void testGetAllPets() throws Exception {
        mockMvc.perform(get("/api/pets"))
                .andExpect(status().isOk());
    }
}
