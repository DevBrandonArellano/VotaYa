package com.votaya.reportservice.controller;

import com.votaya.reportservice.dto.CertificateResponse;
import com.votaya.reportservice.dto.VoteResult;
import com.votaya.reportservice.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/results")
    public ResponseEntity<VoteResult> getResults() {
        VoteResult results = reportService.getAggregatedResults();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/certificate/{cedula}")
    public ResponseEntity<CertificateResponse> getCertificate(@PathVariable String cedula) {
        return reportService.generateCertificate(cedula)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
