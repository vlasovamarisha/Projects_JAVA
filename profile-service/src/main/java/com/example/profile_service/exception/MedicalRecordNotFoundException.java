package com.example.profile_service.exception;

public class MedicalRecordNotFoundException extends RuntimeException {
    public MedicalRecordNotFoundException(String id) {
        super("Medical record not found with id: " + id);
    }
}
