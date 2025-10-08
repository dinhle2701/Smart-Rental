package com.project.SmartRental.auth.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "api_auth", // 👈 tên bạn muốn hiển thị
        description = "API xử lý các nghiệp vụ liên quan đến xác thực"
)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/status")
    public String status() {
        return "Auth service is running.";
    }

    @PostMapping("/login")
    public String login() {
        // Placeholder for login logic
        return "Login endpoint";
    }

    @PostMapping("/register")
    public String register() {
        // Placeholder for registration logic
        return "Register endpoint";
    }
}
