package com.votaya.authservice.service;

import com.votaya.authservice.dto.LoginRequest;
import com.votaya.authservice.dto.CedulaValidationResponse;
import com.votaya.authservice.security.JwtTokenProvider;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AuthenticationService {

    private final JwtTokenProvider jwtTokenProvider;
    private final JdbcTemplate jdbcTemplate;

    public AuthenticationService(JwtTokenProvider jwtTokenProvider, JdbcTemplate jdbcTemplate) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Autentica al usuario y genera un token JWT.
     */
    public String authenticate(LoginRequest loginRequest) {
        // En una implementación real, aquí validarías la firma electrónica antes de generar el token.
        UserDetails userDetails = new User(loginRequest.cedula(), "", new ArrayList<>());
        return jwtTokenProvider.generateToken(userDetails);
    }

    /**
     * Valida la cédula consultando la base de datos de Azure.
     */
    public CedulaValidationResponse validateCedula(String cedula) {
        try {
            // Consulta SQL para buscar en la tabla citizens de Azure
            String sql = "SELECT first_name, last_name FROM citizens WHERE cedula = ?";
            
            // Ejecutamos la consulta usando JdbcTemplate
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, cedula);

            if (!rows.isEmpty()) {
                Map<String, Object> row = rows.get(0);
                String firstName = (String) row.get("first_name");
                String lastName = (String) row.get("last_name");
                
                return new CedulaValidationResponse(firstName, lastName);
            } else {
                // Si la lista está vacía, la cédula no existe en la BD
                return new CedulaValidationResponse("Cédula no encontrada en el padrón electoral.");
            }
        } catch (Exception e) {
            // Log del error en la consola de Docker para depuración
            System.err.println("ERROR DE CONEXIÓN A AZURE: " + e.getMessage());
            return new CedulaValidationResponse("Error técnico al conectar con el padrón: " + e.getMessage());
        }
    }
}