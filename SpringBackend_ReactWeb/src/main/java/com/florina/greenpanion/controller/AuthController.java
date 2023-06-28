package com.florina.greenpanion.controller;

import com.florina.greenpanion.config.JwtUtils;
import com.florina.greenpanion.dto.request.AuthenticationRequest;
import com.florina.greenpanion.dto.request.UserRequest;
import com.florina.greenpanion.dto.response.LoginResponse;
import com.florina.greenpanion.model.User;
import com.florina.greenpanion.repository.UserRepository;
import com.florina.greenpanion.service.AuthService;
import com.florina.greenpanion.service.JpaUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JpaUserDetailsService jpaUserDetailsService;

    private final AuthService authService;

    private final JwtUtils jwtUtils;

    private final UserRepository userRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request, HttpServletResponse response) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword(),
                            new ArrayList<>()));
            final UserDetails user = jpaUserDetailsService.loadUserByUsername(request.getEmail());
            if (user != null) {
                String jwt = jwtUtils.generateToken(user);
                return ResponseEntity.ok(new LoginResponse("User auth successfully", jwt));
            }
            return ResponseEntity.status(400).body("Error authenticating");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(400).body("" + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequest user) throws Exception {
        boolean emailExists = authService.checkEmailExists(user.getEmail());
        if (emailExists) {
            return ResponseEntity.status(409).body("Email already exists");
        }
       User savedUser = authService.AddUser(user).orElseThrow(() -> new Exception("Something wrong happened."));
       final UserDetails userDetails = jpaUserDetailsService.loadUserByUsername(savedUser.getEmail());
        if (user != null) {
            return ResponseEntity.ok("User registered successfully");
        }
        return ResponseEntity.status(400).body("Error register");
    }

    @GetMapping("/admin")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/ranking")
    public List<User> getRanking() {
        return authService.getRanking();
    }

}
