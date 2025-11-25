package com.votaya.reportservice.dto;

import java.time.Instant;

public record CertificateResponse(String message, String voterId, Instant voteTimestamp, String certificateSignature) {
}
