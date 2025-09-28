package com.example.finances.controller;

import com.example.finances.dto.BalanceDto;
import com.example.finances.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/balance/{userId}")
    public BalanceDto getBalance(@PathVariable Long userId) {
        return reportService.getBalanceByUser(userId);
    }
}
