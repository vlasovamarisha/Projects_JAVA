package com.example.shop.controller;

import com.example.shop.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("User Profile: this page for USER role")
    @WithMockUser(roles = "user")
    void testUserProfile() throws Exception {
        Mockito.when(userService.getUserProfile())
                .thenReturn("User Profile: this page for USER role");

        mockMvc.perform(get("/user/profile"))
                .andExpect(status().isOk())
                .andExpect(content().string("User Profile: this page for USER role"));
    }

    @Test
    @DisplayName("Admin Profile: this page for ADMIN role")
    @WithMockUser(roles = "admin")
    void testAdminDashboard() throws Exception {
        Mockito.when(userService.getAdminDashboard())
                .thenReturn("Admin Profile: this page for ADMIN role");

        mockMvc.perform(get("/admin/dashboard"))
                .andExpect(status().isOk())
                .andExpect(content().string("Admin Profile: this page for ADMIN role"));
    }

    @Test
    @DisplayName("Welcome to public page without authorize")
    void testPublicEndpoint() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome to public page without authorize"));
    }
}
