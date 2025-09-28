package com.example.finances.service;

import com.example.finances.dto.BalanceDto;
import com.example.finances.enuns.TransactionType;
import com.example.finances.models.Transaction;
import com.example.finances.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    private final TransactionRepository transactionRepository;

    public ReportService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public BalanceDto getBalanceByUser(Long userId) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);

        Double totalIncome = transactions.stream()
                .filter(t -> t.getType() == TransactionType.ENTRADAS)
                .mapToDouble(Transaction::getValue)
                .sum();

        Double totalExpense = transactions.stream()
                .filter(t -> t.getType() == TransactionType.SAIDAS)
                .mapToDouble(Transaction::getValue)
                .sum();

        return new BalanceDto(totalIncome, totalExpense);
    }
}
