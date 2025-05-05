package com.example.profile_service.model;

import lombok.Data;

import java.util.Date;

@Data
public class Vaccination {
    private String name;
    private Date administrationDate;
    private Date expirationDate;
}
