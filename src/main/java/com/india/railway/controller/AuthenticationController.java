package com.india.railway.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.india.railway.authservice.JwtRequest;
import com.india.railway.authservice.JwtResponse;
import com.india.railway.authservice.JwtUtils;
import com.india.railway.controller.RefreshTokenController.AuthResponse;
import com.india.railway.model.User;
import com.india.railway.repository.UserRepository;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtil;

    @PostMapping("/authenticate")
    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        // authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        final User userDetails = userRepository.findByUsername(
                jwtRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails.getUsername(), userDetails.getRoles());

        return new JwtResponse(jwt);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            throw new Exception("Invalid credentials", e);
        }
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('USERUSER')")
    public String register(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        // user.setPassword("test");
        user.setEmail(user.getEmail());
        user.setMobileno(user.getMobileno());
        // save the user to the database
        // ...
        userRepository.save(user);
        return "User registered successfully";
    }
}
