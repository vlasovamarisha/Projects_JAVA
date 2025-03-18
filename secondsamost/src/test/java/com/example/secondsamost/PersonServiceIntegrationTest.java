package com.example.secondsamost;

import com.example.secondsamost.model.Person;
import com.example.secondsamost.repository.PersonRepository;
import com.example.secondsamost.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PersonServiceIntegrationTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        personRepository.deleteAll(); // Очищаем таблицу перед каждым тестом
    }

    @Test
    void testCreatePerson() {
        Person person = new Person(null, "John Doe", 30);
        Person savedPerson = personService.createPerson(person);

        assertNotNull(savedPerson.getId());
        assertEquals("John Doe", savedPerson.getName());
        assertEquals(30, savedPerson.getAge());
    }

    @Test
    void testGetAllPersons() {
        personService.createPerson(new Person(null, "Alice", 25));
        personService.createPerson(new Person(null, "Bob", 35));

        List<Person> persons = personService.getAllPersons();
        assertEquals(2, persons.size());
    }
}
