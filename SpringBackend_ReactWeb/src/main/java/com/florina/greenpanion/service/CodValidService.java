package com.florina.greenpanion.service;

import com.florina.greenpanion.model.CodValidare;
import com.florina.greenpanion.repository.CodValidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CodValidService {
    private final CodValidRepository codValidRepository;

    public List<CodValidare> findByPointsValidation(int pointsValidation) {
        return codValidRepository.findByPointsValidation(pointsValidation);
    }

    public boolean isCodeValid(String pointsValidation) {
      CodValidare codValidare = codValidRepository.findByPointsValidation(pointsValidation);
        if (codValidare != null) {
          codValidRepository.delete(codValidare);
            System.out.println("Deleted code");
        }
        return codValidare != null;
    }
}
