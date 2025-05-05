package com.example.profile_service.dto;

import lombok.Data;
import java.util.List;

@Data
public class EmergencyMedicalInfo {
    private String petName;
    private String species;
    private String breed;
    private String ownerContact; // Важный контакт владельца
    private List<String> criticalAllergies;
    private List<String> importantVaccinations;
    private List<String> chronicConditions;

    // Дополнительная информация для нашедшего
    private String finderInstructions = "Пожалуйста, свяжитесь с владельцем или доставьте в ближайшую ветклинику";
}