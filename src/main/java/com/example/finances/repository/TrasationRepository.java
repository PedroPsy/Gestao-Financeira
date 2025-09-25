package com.example.finances.repository;

import com.example.finances.models.Trasation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrasationRepository extends JpaRepository<Trasation, Long> {
}
