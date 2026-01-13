package com.votaya.authservice.service;

import com.votaya.authservice.dto.LoginRequest;
import com.votaya.authservice.dto.CedulaValidationResponse; // Import the new DTO
import com.votaya.authservice.security.JwtTokenProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationService(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Authenticates the user based on the login request.
     * In a real application, this would involve validating the electronic signature
     * and checking the voter's status in the database.
     *
     * @param loginRequest The login request containing user credentials.
     * @return A JWT token if authentication is successful.
     */
    public String authenticate(LoginRequest loginRequest) {
        // 1. Simulate FirmaEC validation
        System.out.println("Simulating validation for cedula: " + loginRequest.cedula());

        // 2. Simulate checking for voter uniqueness.
        // This would typically involve a call to another service or database.

        // 3. If valid, create a UserDetails object for token generation.
        // We'll use the 'cedula' as the username.
        UserDetails userDetails = new User(loginRequest.cedula(), "", new ArrayList<>());

        // 4. Generate and return the JWT token.
        return jwtTokenProvider.generateToken(userDetails);
    }

    /**
     * Simulates the call to an external API (like ApiConsult del Ecuador) to validate a cedula
     * and retrieve the associated name and surname.
     *
     * @param cedula The cedula number to validate.
     * @return A CedulaValidationResponse containing the name and surname if found, or an error message.
     */
    public CedulaValidationResponse validateCedula(String cedula) {
        // Simulate API call delay
        try {
            Thread.sleep(500); // 0.5 second delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Hardcoded responses for demonstration purposes
        if ("1234567890".equals(cedula)) {
            return new CedulaValidationResponse("Juan", "Perez");
        } else if ("0987654321".equals(cedula)) {
            return new CedulaValidationResponse("Maria", "Garcia");
        } else {
            return new CedulaValidationResponse("Cédula no encontrada o inválida.");
        }
    }
}
