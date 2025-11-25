package com.votaya.ledgerservice.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.votaya.ledgerservice.entity.Vote;
import com.votaya.ledgerservice.repository.VoteRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Component
public class VoteConsumer {

    private static final String VOTES_QUEUE = "votes-queue";

    private final VoteRepository voteRepository;
    private final ObjectMapper objectMapper;

    public VoteConsumer(VoteRepository voteRepository, ObjectMapper objectMapper) {
        this.voteRepository = voteRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    @RabbitListener(queues = VOTES_QUEUE)
    public void receiveVote(String message) {
        try {
            System.out.println("Received vote message: " + message);

            JsonNode rootNode = objectMapper.readTree(message);
            String cedula = rootNode.get("cedula").asText();
            String payload = rootNode.get("payload").asText();

            Vote vote = new Vote(cedula, payload, Instant.now());
            voteRepository.save(vote);

            System.out.println("Successfully saved vote for cedula: " + cedula);

        } catch (Exception e) {
            System.err.println("Failed to process and save vote: " + e.getMessage());
            // In a real application, you would handle this error, maybe by sending the message to a dead-letter queue.
        }
    }
}
