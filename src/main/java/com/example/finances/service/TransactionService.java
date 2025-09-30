package com.example.finances.service;

import com.example.finances.enuns.TransactionType;
import com.example.finances.exception.ResourceNotFoundException;
import com.example.finances.models.Transaction;
import com.example.finances.models.User;
import com.example.finances.repository.TransactionRepository;
import com.example.finances.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public Page<Transaction> findAllByUser(String username, TransactionType type, LocalDateTime startDate, LocalDateTime endDate, int page, int size) {
        User user = userRepository.findByUsername(username);
        Pageable pageable = PageRequest.of(page, size);
        return transactionRepository.findByFilter(user.getId(), type, startDate, endDate, pageable);
    }

    public Transaction create(Transaction transaction, String username) {
        User user = userRepository.findByUsername(username);
        transaction.setUser(user);
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

    public void delete(Long id) {
        Transaction transactionUp = transactionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Transaction not found"));
        transactionRepository.deleteById(id);
    }

    public Transaction getTrasationById(long id) {
        return transactionRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Transaction not found"));
    }
}
