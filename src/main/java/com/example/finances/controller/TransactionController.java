package com.example.finances.controller;

import com.example.finances.dto.TransactionDto;
import com.example.finances.models.Transaction;
import com.example.finances.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class TransactionController {
    private TransactionService transactionService;
    private ModelMapper modelMapper;
    public TransactionController(TransactionService transactionService, ModelMapper modelMapper) {
        this.transactionService = transactionService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/transactions/{id}")
    public Transaction getTrasation(@PathVariable Long id){
        Transaction transaction = transactionService.getTrasationById(id);
        return transaction;
    }
    @PostMapping("/transactions")
    public Transaction createTrasation(@RequestBody TransactionDto transactionDto){
        Transaction transaction = convertToEntity(transactionDto);
        return transactionService.create(transaction);
    }
    @PutMapping("/transactions/{id}")
    public Transaction updateTrasation(@PathVariable Long id, @RequestBody TransactionDto transactionDto){
        Transaction transaction = convertToEntity(transactionDto);
        return transactionService.update(id, transaction);
    }
    @DeleteMapping("/transactions/{id}")
    public void deleteTrasation(@PathVariable Long id){
        transactionService.delete(id);
    }


    private TransactionDto convertToDTO(Transaction u) {
        return modelMapper.map(u, TransactionDto.class);
    }
    private Transaction convertToEntity(TransactionDto transactionDto) {
        return modelMapper.map(transactionDto, Transaction.class);
    }

}
