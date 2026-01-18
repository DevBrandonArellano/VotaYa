package com.votaya.authservice.dto;

// Using record for immutable, concise DTO
public record LoginRequest(String cedula, String firmaElectronicaData) {
}
