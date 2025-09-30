package com.example.finances.service;

import com.example.finances.exception.UserNotFoundException;
import com.example.finances.models.User;
import com.example.finances.repository.CategoryRepository;
import com.example.finances.repository.TransactionRepository;
import com.example.finances.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, TransactionRepository transactionRepository, CategoryRepository categoryRepository,  BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));
    }
    public User save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    public User update(Long userId, User u) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));
        user.setEmail(u.getEmail());
        user.setUsername(u.getUsername());
        return userRepository.save(user);
    }
    public void delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));
        userRepository.deleteById(userId);
    }

}
