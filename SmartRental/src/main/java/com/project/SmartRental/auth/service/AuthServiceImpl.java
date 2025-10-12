package com.project.SmartRental.auth.service;

import com.project.SmartRental.account.model.Account;
import com.project.SmartRental.account.repository.AccountRepository;
import com.project.SmartRental.auth.dto.req.AuthRequest;
import com.project.SmartRental.auth.dto.res.AuthResponse;
import com.project.SmartRental.auth.dto.res.RegisterResponse;
import com.project.SmartRental.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authManager;
    private final AccountRepository accountRepository;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        Account user = accountRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String accessToken = tokenProvider.generateAccessToken(
                user.getAccountId(),
                user.getAccountName(),
                user.getEmail(),
                user.getRole()
        );

        String refreshToken = tokenProvider.generateRefreshToken(
                user.getAccountId(),
                user.getAccountName(),
                user.getEmail(),
                user.getRole()
        );

        long expiresIn = 86400;

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(expiresIn)
                .user(
                        AuthResponse.UserInfo.builder()
                                .accountId(user.getAccountId())
                                .email(user.getEmail())
                                .accountName(user.getAccountName())
                                .role(user.getRole())
                                .build()
                )
                .build();
    }

    @Override
    public RegisterResponse register(AuthRequest authRequest) {
        if (accountRepository.existsByEmail(authRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Account account = new Account();

        account.setEmail(authRequest.getEmail());
        account.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        account.setCreateAt(LocalDateTime.now());
        account.setUpdateAt(LocalDateTime.now());
        account.setRole("USER");

        // 🎲 Sinh mã accountCode: USER.xxxx (4 số ngẫu nhiên)
        String randomCode = String.format("%04d", new Random().nextInt(10000)); // từ 0000 -> 9999
        String accountCode = "USER." + randomCode;
        account.setAccountCode(accountCode);

        Account saved = accountRepository.save(account);
        return RegisterResponse.builder()
                .accountId(saved.getAccountId())
                .email(authRequest.getEmail())
                .accountCode(saved.getAccountCode())
                .role(saved.getRole())
                .build();
    }
}
