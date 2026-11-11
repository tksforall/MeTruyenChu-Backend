package com.example.mtc.controller;

import com.example.mtc.dto.RegisterRequest;
import com.example.mtc.dto.UserResponse;
import com.example.mtc.entity.User;
import com.example.mtc.security.JwtService;
import com.example.mtc.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService service;
    private final JwtService jwtService;

    public AuthController(
            UserService service,
            JwtService jwtService
    ) {
        this.service = service;
        this.jwtService = jwtService;
    }


    // ======================================================
    // REGISTER
    // ======================================================

    @PostMapping("/register")
    public UserResponse register(
            @RequestBody RegisterRequest request
    ) {

        User user = service.register(request);

        return toResponse(user);
    }


    // ======================================================
    // LOGIN
    // ======================================================

    @PostMapping("/login")
    public LoginResponse login(
            @RequestParam String email,
            @RequestParam String password
    ) {

        // Kiểm tra email + password
        User user = service.login(
                email,
                password
        );

        // Tạo JWT
        String token = jwtService.generateToken(
                user.getUserId(),
                user.getEmail()
        );

        return new LoginResponse(
                token,
                toResponse(user)
        );
    }


    // ======================================================
    // USER → RESPONSE
    // ======================================================

    private UserResponse toResponse(User user) {

        return new UserResponse(
                user.getUserId(),
                user.getUserName(),
                user.getEmail(),
                user.getAvatarUrl(),
                user.getRecommendationCount(),
                user.getStatus(),
                user.getAuthProvider(),
                user.getEmailVerified(),
                user.getPhone(),
                user.getGender()
        );
    }

    // ======================================================
    // LOGIN RESPONSE
    // ======================================================

    public record LoginResponse(
            String token,
            UserResponse user
    ) {
    }
}