package com.example.shop.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public String getUserProfile() {
        return "User Profile: this page for USER role";
    }

    public String getAdminDashboard() {
        return "Admin Profile: this page for ADMIN role";
    }
}
