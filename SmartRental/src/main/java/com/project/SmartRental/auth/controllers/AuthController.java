package com.project.SmartRental.auth.controllers;

import com.project.SmartRental.account.model.Account;
import com.project.SmartRental.account.repository.AccountRepository;
import com.project.SmartRental.auth.dto.req.AuthRequest;
import com.project.SmartRental.auth.dto.res.AuthResponse;
import com.project.SmartRental.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDateTime;

@Tag(
        name = "api_auth", // 👈 tên bạn muốn hiển thị
        description = "API xử lý các nghiệp vụ liên quan đến xác thực"
)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/status")
    public String status() {
        return "Auth service is running.";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );

            // 2️⃣ Lấy thông tin user từ DB
            Account user = accountRepository.findByEmail(authRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));


            String token = tokenProvider.generateToken(authRequest.getEmail());
            long expiresIn = 86400;
            AuthResponse response = AuthResponse.builder()
                    .accessToken(token)
                    .tokenType("Bearer")
                    .expiresIn(expiresIn)
                    .user(
                            AuthResponse.UserInfo.builder()
                                    .accountId(user.getAccountId())
                                    .email(user.getEmail())
                                    .accountName(user.getAccountName()) // <-- Gán role tại đây
                                    .role(user.getRole())
                                    .build()
                    )
                    .build();

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Account account) {
        if (accountRepository.existsByEmail(account.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setCreateAt(LocalDateTime.now());
        account.setUpdateAt(LocalDateTime.now());
        account.setRole("ADMIN");
        accountRepository.save(account);
        return ResponseEntity.ok("Account registered successfully");
    }
}
