package com.example.profile_service.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
//import com.example.profile_service.model.ChronicDisease;
import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "medical_records")
public class MedicalRecord {
    @Id
    private String id;
    private String petId;
    private String emergencyAccessCode;
    private List<Vaccination> vaccinations;
    private List<String> allergies;
    private List<ChronicDiseases> chronicDiseases;
    private LocalDateTime codeExpirationDate;

    public MedicalRecord() {
        this.emergencyAccessCode = UUID.randomUUID().toString().substring(0, 8);
        this.codeExpirationDate = LocalDateTime.now().plusYears(1);
    }
}

