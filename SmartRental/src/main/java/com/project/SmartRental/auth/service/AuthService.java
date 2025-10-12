package com.project.SmartRental.auth.service;

import com.project.SmartRental.auth.dto.req.AuthRequest;
import com.project.SmartRental.auth.dto.res.AuthResponse;
import com.project.SmartRental.auth.dto.res.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    AuthResponse login(AuthRequest authRequest);
    RegisterResponse register(AuthRequest authRequest);
}
