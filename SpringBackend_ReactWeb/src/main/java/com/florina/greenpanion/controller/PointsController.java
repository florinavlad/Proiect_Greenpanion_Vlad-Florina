package com.florina.greenpanion.controller;

import com.florina.greenpanion.repository.CodValidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/points")
@CrossOrigin("*")

public class PointsController {

    @Autowired
    CodValidRepository codValidRepository;

    @GetMapping("/check")
    public ResponseEntity<?> checkCodeValidity(@RequestParam("code") String code) {
        return ResponseEntity.ok().body(codValidRepository.findAll()
                .stream()
                .anyMatch(codValidare -> codValidare.getPointsValidation().equals(code)));
    }
}
