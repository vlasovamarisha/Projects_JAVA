package com.example.shop.controller;

import com.example.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/profile")
    @PreAuthorize("hasRole('user')")
    public String userProfile() {
        return userService.getUserProfile();
    }

    @GetMapping("/admin/dashboard")
    @PreAuthorize("hasRole('admin')")
    public String adminDashboard() {
        return userService.getAdminDashboard();
    }

    @GetMapping("/")
    public String publicEndpoint() {
        return "Welcome to public page without authorize";
    }
}
