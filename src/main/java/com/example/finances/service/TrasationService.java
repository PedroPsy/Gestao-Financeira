package com.example.finances.service;

import com.example.finances.models.Trasation;
import com.example.finances.models.User;
import com.example.finances.repository.CategoryRepository;
import com.example.finances.repository.TrasationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrasationService {
    private final TrasationRepository trasationRepository;
    public TrasationService(TrasationRepository trasationRepository, CategoryRepository categoryRepository) {
        this.trasationRepository = trasationRepository;
    }
    public Trasation create(Trasation trasation) {
        return trasationRepository.save(trasation);
    }
    public Trasation update(Long transatioId, Trasation trasation) {
        Optional<Trasation> tra = trasationRepository.findById(transatioId);
        if(tra.isPresent()){
            Trasation trasationUp = tra.get();
            trasationUp.setType(trasation.getType());
            trasation.setCategoryId(trasation.getCategoryId());
            trasation.setDescription(trasation.getDescription());
            trasation.setValue(trasation.getValue());
            return trasationRepository.save(trasationUp);
        }
        return null;
    }
    public void delete(Trasation trasation) {
        trasationRepository.delete(trasation);
    }
    public void delete(Long id) {
        trasationRepository.deleteById(id);
    }
    public Trasation findById(Long id) {
        return trasationRepository.findById(id).orElse(null);
    }
    public Trasation getTrasationById(long id) {
        return trasationRepository.getOne(id);
    }
    public Trasation getTrasationByUser(User user) {
        return trasationRepository.getOne(user.getId());
    }
}
