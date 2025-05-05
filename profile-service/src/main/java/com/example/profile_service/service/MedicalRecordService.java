package com.example.profile_service.service;

import com.example.profile_service.dto.EmergencyMedicalInfo;
import com.example.profile_service.exception.MedicalRecordNotFoundException;
import com.example.profile_service.model.MedicalRecord;
import com.example.profile_service.model.Pet;
import com.example.profile_service.model.Vaccination;
import com.example.profile_service.repository.MedicalRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final PetService petService;

    public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    public MedicalRecord getMedicalRecordByPetId(String petId) {
        return medicalRecordRepository.findByPetId(petId)
                .orElseThrow(() -> new RuntimeException("Medical record not found"));
    }

    public MedicalRecord updateMedicalRecord(String petId, MedicalRecord medicalRecordDetails) {
        MedicalRecord medicalRecord = getMedicalRecordByPetId(petId);
        medicalRecord.setVaccinations(medicalRecordDetails.getVaccinations());
        medicalRecord.setAllergies(medicalRecordDetails.getAllergies());
        medicalRecord.setChronicDiseases(medicalRecordDetails.getChronicDiseases());
        return medicalRecordRepository.save(medicalRecord);
    }

    public void addVaccination(String petId, Vaccination vaccination) {
        MedicalRecord medicalRecord = getMedicalRecordByPetId(petId);
        medicalRecord.getVaccinations().add(vaccination);
        medicalRecordRepository.save(medicalRecord);
    }

    public void deleteMedicalRecord(String petId) {
        medicalRecordRepository.deleteByPetId(petId);
    }

    public MedicalRecord getMedicalRecordById(String id) {
        return medicalRecordRepository.findById(id)
                .orElseThrow(() -> new MedicalRecordNotFoundException(id));
    }

    public MedicalRecord findByEmergencyCode(String emergencyAccessCode) {
        return medicalRecordRepository.findByEmergencyAccessCode(emergencyAccessCode)
                .orElseThrow(() -> new MedicalRecordNotFoundException("Emergency code: " + emergencyAccessCode));
    }

    public EmergencyMedicalInfo getEmergencyInfo(String accessCode) {
        MedicalRecord record = medicalRecordRepository.findByEmergencyAccessCode(accessCode)
                .orElseThrow(() -> new MedicalRecordNotFoundException(accessCode));

        Pet pet = petService.getPetById(record.getPetId());

        EmergencyMedicalInfo info = new EmergencyMedicalInfo();
        info.setPetName(pet.getName());
        info.setSpecies(pet.getSpecies());
        info.setBreed(pet.getBreed());
        info.setCriticalAllergies(record.getAllergies());

        return info;
    }
}
