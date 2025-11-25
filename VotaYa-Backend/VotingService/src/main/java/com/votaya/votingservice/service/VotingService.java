package com.votaya.votingservice.service;

import com.votaya.votingservice.config.MessagingConfig;
import com.votaya.votingservice.dto.VoteRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VotingService {

    private final RabbitTemplate rabbitTemplate;
    // Simple in-memory cache to simulate idempotency key tracking
    private final Map<String, Boolean> idempotencyCache = new ConcurrentHashMap<>();

    public VotingService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void processVote(VoteRequest voteRequest, String idempotencyKey) {
        // 1. Idempotency Check
        if (idempotencyCache.containsKey(idempotencyKey)) {
            System.out.println("Duplicate request detected with key: " + idempotencyKey);
            return; // Request already processed, do nothing.
        }

        // 2. Get User from Security Context
        String cedula = SecurityContextHolder.getContext().getAuthentication().getName();

        // 3. Publish vote asynchronously
        // In a real scenario, the message would be more structured, maybe a dedicated object
        String message = "{\"cedula\":\"" + cedula + "\", \"payload\":\"" + voteRequest.encryptedVotePayload() + "\"}";
        rabbitTemplate.convertAndSend(MessagingConfig.VOTES_QUEUE, message);

        System.out.println("Vote from cedula " + cedula + " published to queue.");

        // 4. Mark idempotency key as processed
        idempotencyCache.put(idempotencyKey, true);
    }
}
