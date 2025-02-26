package com.example.authorinfo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAuthorInfo() throws Exception {
        mockMvc.perform(get("/author"))
                .andExpect(status().isOk()) // Проверяем, что HTTP-статус 200
                .andExpect(view().name("author")) // Проверяем, что возвращается имя представления "author"
                .andExpect(model().attribute("name", "Власова Марина")) // Проверяем, что модель содержит атрибут "name"
                .andExpect(model().attribute("group", "ИП-217")); // Проверяем, что модель содержит атрибут "description"
    }
}
