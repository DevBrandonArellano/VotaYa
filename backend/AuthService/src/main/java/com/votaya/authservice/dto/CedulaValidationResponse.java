package com.votaya.authservice.dto;

public class CedulaValidationResponse {
    private String firstName;
    private String lastName;
    private String message; // For error messages or status

    public CedulaValidationResponse(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.message = "Cédula validada exitosamente.";
    }

    public CedulaValidationResponse(String message) {
        this.message = message;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMessage() {
        return message;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
