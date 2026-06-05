package com.warehouse.config;

import com.warehouse.entity.User;
import com.warehouse.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
public class SecurityConfig {

    // Tell Spring Security how to load users
    @Bean
    public UserDetailsService userDetailsService(
            UserRepository userRepository) {

        return username -> {
            // Load user from your database
            User user = userRepository
                    .findByUsername(username)
                    .orElseThrow(() ->
                            new UsernameNotFoundException(
                                    "User not found: " + username));

            // Convert your User to Spring Security User
            return org.springframework.security.core
                    .userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .authorities(List.of(
                            new SimpleGrantedAuthority(
                                    "ROLE_" + user.getRole().name())))
                    .build();
        };
    }

    // Url-based role restrictions
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth

                        // Auth endpoints: everyone
                        .requestMatchers("/auth/**")
                        .permitAll()

                        // CSV upload: ADMIN only
                        .requestMatchers(HttpMethod.POST, "/csv/**")
                        .hasRole("ADMIN")

                        // View products: everyone
                        .requestMatchers(HttpMethod.GET, "/products/**")
                        .permitAll()

                        // Add product: ADMIN only
                        .requestMatchers(HttpMethod.POST, "/products/**")
                        .hasRole("ADMIN")

                        // Update product: ADMIN only
                        .requestMatchers(HttpMethod.PUT, "/products/**")
                        .hasRole("ADMIN")

                        // Delete product: ADMIN only
                        .requestMatchers(HttpMethod.DELETE, "/products/**")
                        .hasRole("ADMIN")

                        // Everything else: must be logged in
                        .anyRequest()
                        .authenticated()
                )

                // Use HTTP Basic Auth
                // Postman sends: username + password in header
                .httpBasic(basic -> {});

        return http.build();
    }

    // BCrypt password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
