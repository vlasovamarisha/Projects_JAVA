package com.example.profile_service.controller;

import org.springframework.ui.Model; // Правильный импорт
import com.example.profile_service.dto.EmergencyMedicalInfo;
import com.example.profile_service.model.ChronicDiseases;
import com.example.profile_service.model.MedicalRecord;
import com.example.profile_service.model.Pet;
import com.example.profile_service.model.Vaccination;
import com.example.profile_service.service.MedicalRecordService;
import com.example.profile_service.service.PetService;
import com.example.profile_service.service.QrCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;
    private final QrCodeService qrCodeService;
    private final PetService petService;

    @PostMapping
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        return ResponseEntity.ok(medicalRecordService.createMedicalRecord(medicalRecord));
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<MedicalRecord> getMedicalRecord(@PathVariable String petId) {
        return ResponseEntity.ok(medicalRecordService.getMedicalRecordByPetId(petId));
    }

    @PutMapping("/pet/{petId}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(
            @PathVariable String petId, @RequestBody MedicalRecord medicalRecord) {
        return ResponseEntity.ok(medicalRecordService.updateMedicalRecord(petId, medicalRecord));
    }

    @PostMapping("/pet/{petId}/vaccinations")
    public ResponseEntity<Void> addVaccination(
            @PathVariable String petId, @RequestBody Vaccination vaccination) {
        medicalRecordService.addVaccination(petId, vaccination);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/pet/{petId}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable String petId) {
        medicalRecordService.deleteMedicalRecord(petId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pet/{petId}/qr-code")
    public ResponseEntity<byte[]> getMedicalRecordQrCode(@PathVariable String petId) throws Exception {
        MedicalRecord record = medicalRecordService.getMedicalRecordByPetId(petId);
        String recordUrl = "http://localhost:8080/api/medical-records/emergency/" + record.getEmergencyAccessCode();
        byte[] qrCode = qrCodeService.generateQrCode(recordUrl, 200, 200);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qrCode);
    }

    @GetMapping("/byid/{recordId}")
    @ResponseStatus(HttpStatus.OK)
    public MedicalRecord getEmergencyMedicalRecord(@PathVariable String recordId) {
        return medicalRecordService.getMedicalRecordById(recordId);
    }

    @GetMapping("/emergency/{accessCode}")
    public ResponseEntity<EmergencyMedicalInfo> getEmergencyInfo(
            @PathVariable String accessCode,
            @RequestParam(required = false) Boolean full) {

        MedicalRecord record = medicalRecordService.findByEmergencyCode(accessCode);
        Pet pet = petService.getPetById(record.getPetId());

        EmergencyMedicalInfo info = new EmergencyMedicalInfo();
        info.setPetName(pet.getName());
        info.setSpecies(pet.getSpecies());
        info.setBreed(pet.getBreed());

        // Только критические аллергии
        info.setCriticalAllergies(record.getAllergies());

        // Только актуальные вакцины
        info.setImportantVaccinations(record.getVaccinations().stream()
                .filter(v -> v.getExpirationDate().after(new Date()))
                .map(Vaccination::getName)
                .collect(Collectors.toList()));

        // Хронические заболевания
        info.setChronicConditions(record.getChronicDiseases().stream()
                .map(ChronicDiseases::getName)
                .collect(Collectors.toList()));

        // Полная информация только по специальному запросу
        if (Boolean.TRUE.equals(full)) {
            info.setOwnerContact("+7 800 555-35-35");
        }

        return ResponseEntity.ok(info);
    }
}