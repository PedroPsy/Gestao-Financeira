package com.example.finances.controller;

import com.example.finances.dto.BalanceDto;
import com.example.finances.models.User;
import com.example.finances.repository.UserRepository;
import com.example.finances.service.ReportService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;
    private final UserRepository userRepository;

    public ReportController(ReportService reportService,  UserRepository userRepository) {
        this.reportService = reportService;
        this.userRepository = userRepository;
    }

    @GetMapping("/balance")
    public BalanceDto getBalance(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByName(userDetails.getUsername());
        return reportService.getBalanceByUser(user.getId());
    }
}
