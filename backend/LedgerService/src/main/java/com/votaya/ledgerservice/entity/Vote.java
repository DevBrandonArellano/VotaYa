package com.votaya.ledgerservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.Instant;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String voterId; // This is the 'cedula'

    private String encryptedVotePayload;

    private Instant timestamp;

    public Vote() {
    }

    public Vote(String voterId, String encryptedVotePayload, Instant timestamp) {
        this.voterId = voterId;
        this.encryptedVotePayload = encryptedVotePayload;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public String getEncryptedVotePayload() {
        return encryptedVotePayload;
    }

    public void setEncryptedVotePayload(String encryptedVotePayload) {
        this.encryptedVotePayload = encryptedVotePayload;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
