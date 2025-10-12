package com.project.SmartRental.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.SmartRental.auth.dto.req.AuthRequest;
import com.project.SmartRental.auth.dto.res.AuthResponse;
import com.project.SmartRental.auth.dto.res.RegisterResponse;
import com.project.SmartRental.auth.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
        name = "api_auth", // 👈 tên bạn muốn hiển thị
        description = "API xử lý các nghiệp vụ liên quan đến xác thực"
)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/status")
    public String status() {
        return "Auth service is running.";
    }

    @Operation(
            summary = "Login account",
            description = "Đăng nhập tài khoản"
    )
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        AuthResponse authResponse = authService.login(authRequest);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Register account",
            description = "Tạo mới tài khoản"
    )
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody AuthRequest authRequest) {
        RegisterResponse registerResponse = authService.register(authRequest);
        return new ResponseEntity<>(registerResponse, HttpStatus.OK);
    }
}
