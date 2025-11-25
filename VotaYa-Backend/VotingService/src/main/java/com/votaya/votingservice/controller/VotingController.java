package com.votaya.votingservice.controller;

import com.votaya.votingservice.dto.VoteRequest;
import com.votaya.votingservice.service.VotingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/voting")
public class VotingController {

    private final VotingService votingService;

    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }

    @PostMapping("/emitir")
    public ResponseEntity<Void> emitVote(@RequestBody VoteRequest voteRequest,
                                         @RequestHeader("Idempotency-Key") String idempotencyKey) {
        // The service will handle the logic of idempotency and asynchronous publishing
        votingService.processVote(voteRequest, idempotencyKey);

        // Return 202 Accepted to indicate the request has been accepted for processing
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
