package com.example.finances.service;

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
    public Transaction update(Long transatioId, Transaction transaction) {
        Optional<Transaction> tra = transactionRepository.findById(transatioId);
        if(tra.isPresent()){
            Transaction transactionUp = tra.get();
            transactionUp.setType(transaction.getType());
            transactionUp.setCategoryId(transaction.getCategoryId());
            transactionUp.setDescription(transaction.getDescription());
            transactionUp.setValue(transaction.getValue());

            return transactionRepository.save(transactionUp);
        }
        return null;
    }
    public void delete(Transaction transaction) {
        transactionRepository.delete(transaction);
    }
    public void delete(Long id) {
        transactionRepository.deleteById(id);
    }
    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }
    public Transaction getTrasationById(long id) {
        return transactionRepository.getOne(id);
    }
    public Transaction getTrasationByUser(User user) {
        return transactionRepository.getOne(user.getId());
    }
}
