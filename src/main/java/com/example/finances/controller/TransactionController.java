package com.example.finances.controller;

import com.example.finances.dto.TransactionDto;
import com.example.finances.enuns.TransactionType;
import com.example.finances.models.Transaction;
import com.example.finances.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class TransactionController {
    private TransactionService transactionService;
    private ModelMapper modelMapper;

    public TransactionController(TransactionService transactionService, ModelMapper modelMapper) {
        this.transactionService = transactionService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/transactions")
    public Page<Transaction> getTransactions(
            Authentication authentication,
            @RequestParam(required = false) TransactionType type,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        String username = authentication.getName();
        return transactionService.findAllByUser(username, type, from, to, page, size);
    }

    @GetMapping("/transactions/{id}")
    public Transaction getTrasation(@PathVariable("id") Long id){
        Transaction transaction = transactionService.getTrasationById(id);
        return transaction;
    }

    @PostMapping("/transactions")
    public Transaction createTrasation(@RequestBody TransactionDto transactionDto, Authentication authentication){
        String username = authentication.getName();
        Transaction transaction = convertToEntity(transactionDto);
        return transactionService.create(transaction, username);
    }

    @PutMapping("/transactions/{id}")
    public Transaction updateTrasation(@PathVariable("id") Long id, @RequestBody TransactionDto transactionDto){
        Transaction transaction = convertToEntity(transactionDto);
        return transactionService.update(id, transaction);
    }

    @DeleteMapping("/transactions/{id}")
    public void deleteTrasation(@PathVariable("id") Long id){
        transactionService.delete(id);
    }

    private Transaction convertToEntity(TransactionDto transactionDto) {
        return modelMapper.map(transactionDto, Transaction.class);
    }

}
