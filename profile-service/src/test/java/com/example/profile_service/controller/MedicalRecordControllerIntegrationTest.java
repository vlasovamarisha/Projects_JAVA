package com.example.profile_service.controller;

import com.example.profile_service.model.MedicalRecord;
import com.example.profile_service.model.Pet;
import com.example.profile_service.model.Vaccination;
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

import java.util.Collections;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class MedicalRecordControllerIntegrationTest {

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

    private String petId;

    @BeforeEach
    void setupPet() throws Exception {
        // Create pet to associate with medical record
        Pet pet = new Pet();
        pet.setName("Max");
        pet.setSpecies("Dog");
        pet.setBreed("Bulldog");
        pet.setOwnerId("owner001");

        String petJson = objectMapper.writeValueAsString(pet);

        String response = mockMvc.perform(post("/api/pets")
                        .contentType("application/json")
                        .content(petJson))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Pet createdPet = objectMapper.readValue(response, Pet.class);
        petId = createdPet.getId();
    }

    @Test
    void testCreateAndGetMedicalRecord() throws Exception {
        MedicalRecord record = new MedicalRecord();
        record.setPetId(petId);
        record.setAllergies(Collections.singletonList("Peanuts"));

        String json = objectMapper.writeValueAsString(record);

        // Create
        mockMvc.perform(post("/api/medical-records")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.petId").value(petId));

        // Get
        mockMvc.perform(get("/api/medical-records/pet/{petId}", petId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.petId").value(petId));
    }

}
