package com.warehouse.controller;

import com.warehouse.dto.AuthRequestDTO;
import com.warehouse.dto.UserDTO;
import com.warehouse.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    // Register
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody AuthRequestDTO dto) {
        UserDTO user = service.register(dto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    // Login
    // With HTTP Basic: login happens automatically
    // This endpoint just confirms who you are
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequestDTO dto) {
        String result = service.login(dto);
        return ResponseEntity.ok(result);
    }

    // Create Admin
    // Only call this once to setup admin
    @PostMapping("/create-admin")
    public ResponseEntity<UserDTO> createAdmin(@RequestBody AuthRequestDTO dto) {
        UserDTO admin = service.createAdmin(dto);
        return new ResponseEntity<>(admin, HttpStatus.CREATED);
    }
}
