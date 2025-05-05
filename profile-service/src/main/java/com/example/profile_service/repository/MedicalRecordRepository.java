package com.example.profile_service.repository;

import com.example.profile_service.model.MedicalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, String> {
    Optional<MedicalRecord> findByPetId(String petId); // Изменено на Optional
    Optional<MedicalRecord> findByEmergencyAccessCode(String emergencyAccessCode);

    // Добавляем отсутствующий метод
    void deleteByPetId(String petId);
}
