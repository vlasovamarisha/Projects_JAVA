package com.example.profile_service.model;

import lombok.Data;

import java.util.Date;

@Data
public class ChronicDiseases {
    private String name;
    private String description;
    private Date diagnosisDate;
}

