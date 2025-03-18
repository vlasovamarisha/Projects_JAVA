package com.example.secondsamost;

import com.example.secondsamost.model.Person;
import com.example.secondsamost.service.PersonService;
import com.example.secondsamost.controller.PersonController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TestPerson {

    private PersonService personService;
    private PersonController personController;

    @BeforeEach
    public void setUp() {
        personService = Mockito.mock(PersonService.class);
        personController = new PersonController(personService);
    }

    @Test
    public void testCreatePerson() {
        Person newPerson = new Person(200L,"John", 20);
        when(personService.createPerson(any(Person.class))).thenReturn(newPerson);

        ResponseEntity<Person> response = personController.createPerson(newPerson);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John", response.getBody().getName());
        assertEquals(20, response.getBody().getAge());
    }

    @Test
    public void testGetPerson() {
        Person newPerson = new Person(1L, "John", 20);
        when(personService.createPerson(any(Person.class))).thenReturn(newPerson);

        ResponseEntity<Person> response = personController.createPerson(newPerson);

        assertEquals(1, response.getBody().getId());
    }
}
