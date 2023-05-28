package com.florina.greenpanion.repository;

import com.florina.greenpanion.model.CodValidare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodValidRepository extends JpaRepository<CodValidare, Long> {
    List<CodValidare> findByPointsValidation(int pointsValidation);
    CodValidare findByPointsValidation(String pointsValidation);
}
