package com.example.profile_service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "pets")
public class Pet {
    @Id
    private String id;
    private String name;
    private String species; // вид (кошка, собака и т.д.)
    private String breed; // порода
    private LocalDate birthDate;
    private String chipNumber; // номер чипа
    private String ownerId; // ID владельца
}
