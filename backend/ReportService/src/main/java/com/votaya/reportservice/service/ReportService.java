package com.votaya.reportservice.service;

import com.votaya.reportservice.dto.CertificateResponse;
import com.votaya.reportservice.dto.VoteResult;
import com.votaya.reportservice.entity.Vote;
import com.votaya.reportservice.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ReportService {

    private final VoteRepository voteRepository;

    public ReportService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public VoteResult getAggregatedResults() {
        // This is a simplified result, just counting all votes.
        // A real implementation would involve decrypting and counting votes per candidate.
        long totalVotes = voteRepository.count();
        return new VoteResult(totalVotes);
    }

    public Optional<CertificateResponse> generateCertificate(String cedula) {
        return voteRepository.findByVoterId(cedula)
                .map(this::createCertificateFromVote);
    }

    private CertificateResponse createCertificateFromVote(Vote vote) {
        String message = "This certifies that the holder voted successfully.";
        // Simulate a digital signature for the certificate
        String signature = "SIGNED-" + UUID.randomUUID();
        return new CertificateResponse(message, vote.getVoterId(), vote.getTimestamp(), signature);
    }
}
