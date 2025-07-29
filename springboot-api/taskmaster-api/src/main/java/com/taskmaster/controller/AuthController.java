package com.taskmaster.controller;

import com.taskmaster.model.User;
import com.taskmaster.model.network.AuthRequest;
import com.taskmaster.model.network.AuthResponse;
import com.taskmaster.model.network.RegisterRequest;
import com.taskmaster.repository.UserRepository;
import com.taskmaster.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(new AuthResponse("Username already taken"));
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String token = jwtService.generateToken(request.getEmail());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
