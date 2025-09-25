package com.example.finances.service;

import com.example.finances.models.Category;
import com.example.finances.models.User;
import com.example.finances.repository.CategoryRepository;
import com.example.finances.repository.TrasationRepository;
import com.example.finances.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TrasationRepository trasationRepository;
    private final CategoryRepository categoryRepository;
    public UserService(UserRepository userRepository, TrasationRepository trasationRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.trasationRepository = trasationRepository;
        this.categoryRepository = categoryRepository;
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User save(User user) {
        return userRepository.save(user);
    }
    public User update(Long userId, User u) {
        Optional<User> userOpt = userRepository.findById(userId);
        if(userOpt.isPresent()){
            User user = userOpt.get();
            user.setEmail(u.getEmail());
            user.setName(u.getName());
            return userRepository.save(user);
        }
        return null;
    }
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

}
