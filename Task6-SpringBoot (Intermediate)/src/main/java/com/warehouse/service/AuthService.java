package com.warehouse.service;

import com.warehouse.dto.AuthRequestDTO;
import com.warehouse.dto.UserDTO;
import com.warehouse.entity.User;
import com.warehouse.enums.Role;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    // Register
    public UserDTO register(AuthRequestDTO dto) {

        // Validate username
        if (dto.getUsername() == null || dto.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        // Validate password
        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        // Check duplicate
        if (repo.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Username already exists: " + dto.getUsername());
        }

        // Determine role:
        // If role provided in request AND it's ADMIN
        // only allow if you want to support ADMIN creation
        // For security: hardcode USER role
        Role assignedRole = Role.USER; // always USER

        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(assignedRole)
                .build();

        User saved = repo.save(user);

        return UserDTO.builder()
                .id(saved.getId())
                .username(saved.getUsername())
                .role(saved.getRole())
                .build();
    }

    // Login
    public String login(AuthRequestDTO dto) {

        if (dto.getUsername() == null || dto.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        User user = repo.findByUsername(dto.getUsername())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found: " + dto.getUsername()));

        if (!passwordEncoder.matches(
                dto.getPassword(),
                user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        return "Login successful! Welcome " + user.getUsername() + " [" + user.getRole() + "]";
    }

    // Create admin (special method)
    // Call this once to create admin user
    public UserDTO createAdmin(AuthRequestDTO dto) {

        if (repo.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.ADMIN) // ADMIN role
                .build();

        User saved = repo.save(user);

        return UserDTO.builder()
                .id(saved.getId())
                .username(saved.getUsername())
                .role(saved.getRole())
                .build();
    }
}
