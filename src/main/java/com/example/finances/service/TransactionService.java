package com.example.finances.service;

import com.example.finances.exception.ResourceNotFoundException;
import com.example.finances.models.Transaction;
import com.example.finances.models.User;
import com.example.finances.repository.CategoryRepository;
import com.example.finances.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
    }
    public Transaction create(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    public Transaction update(Long transationId, Transaction transaction) {
        Transaction transactionUp = transactionRepository.findById(transationId).orElseThrow(()-> new ResourceNotFoundException("Transaction not found"));
        transactionUp.setType(transaction.getType());
        transactionUp.setCategoryId(transaction.getCategoryId());
        transactionUp.setDescription(transaction.getDescription());
        transactionUp.setValue(transaction.getValue());
        return transactionRepository.save(transactionUp);
    }
    public void delete(Transaction transaction) {
        transactionRepository.delete(transaction);
    }
    public void delete(Long id) {
        Transaction transactionUp = transactionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Transaction not found"));
        transactionRepository.deleteById(id);
    }
    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Transaction not found"));
    }
    public Transaction getTrasationById(long id) {
        return transactionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Transaction not found"));
    }
    public Transaction getTrasationByUser(User user) {
        return transactionRepository.getOne(user.getId());
    }
}
