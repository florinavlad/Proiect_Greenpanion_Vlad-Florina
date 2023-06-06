package com.florina.greenpanion.repository;

import com.florina.greenpanion.model.CodValidare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CodValidRepository extends JpaRepository<CodValidare, Long> {
    List<CodValidare> findByPointsValidation(int pointsValidation);
    CodValidare findByPointsValidation(String pointsValidation);
}
