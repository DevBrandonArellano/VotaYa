package com.votaya.votingservice.dto;

/**
 * Represents the encrypted vote data sent from the frontend.
 * @param encryptedVotePayload The vote content, encrypted on the client side.
 */
public record VoteRequest(String encryptedVotePayload) {
}
