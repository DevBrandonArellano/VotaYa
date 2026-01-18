package com.votaya.reportservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Immutable;

import java.time.Instant;

@Entity
@Immutable // Marks this entity as read-only for this service
public class Vote {

    @Id
    private Long id;

    private String voterId;

    private String encryptedVotePayload;

    private Instant timestamp;

    // Getters
    public Long getId() {
        return id;
    }

    public String getVoterId() {
        return voterId;
    }

    public String getEncryptedVotePayload() {
        return encryptedVotePayload;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
