package com.votaya.authservice.controller;

import com.votaya.authservice.dto.JwtResponse;
import com.votaya.authservice.dto.LoginRequest;
import com.votaya.authservice.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login-firmaec")
    public ResponseEntity<JwtResponse> loginWithFirmaEc(@RequestBody LoginRequest loginRequest) {
        // Here you would invoke a simulated FirmaEc module to validate the certificate
        // For now, we'll assume validation is successful and proceed to generate a token
        String token = authenticationService.authenticate(loginRequest);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
