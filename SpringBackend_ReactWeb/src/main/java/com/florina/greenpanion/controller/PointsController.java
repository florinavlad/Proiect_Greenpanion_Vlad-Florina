package com.florina.greenpanion.controller;

import com.florina.greenpanion.dto.response.PointsRequest;
import com.florina.greenpanion.model.User;
import com.florina.greenpanion.repository.CodValidRepository;
import com.florina.greenpanion.repository.UserRepository;
//import com.florina.greenpanion.service.EmailService;
import com.florina.greenpanion.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/points")
@CrossOrigin("*")

public class PointsController {

    @Autowired
    CodValidRepository codValidRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/check")
    public ResponseEntity<?> checkCodeValidity(@RequestParam("code") String code) {
        return ResponseEntity.ok().body(codValidRepository.findAll()
                .stream()
                .anyMatch(codValidare -> codValidare.getPointsValidation().equals(code)));
    }

    @PostMapping("/savePoints")
    public ResponseEntity<?> saveUserPoints(@RequestBody PointsRequest pointsRequest) {
        try {
            Optional<User> optionalUser = userRepository.findByEmail(pointsRequest.getEmail());
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setPoints(pointsRequest.getUserPoints());
                userRepository.save(user);
                return ResponseEntity.ok("Success");
            } else {
                return ResponseEntity.badRequest().body("No user in database");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving points");
        }
    }
    @GetMapping("/getPoints")
    public ResponseEntity<?> getUserPoints(@RequestParam String email) {
        try {
            Optional<User> optionalUser = userRepository.findByEmail(email);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                int points = user.getPoints();
                return ResponseEntity.ok().body(points);
            } else {
                return ResponseEntity.badRequest().body("No user in database");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving points");
        }
    }

    @PostMapping("/send-congratulations-email")
    public ResponseEntity<?> sendCongratulationsEmail(@RequestParam("email") String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPoints() >= 1500) {
            User foundUser = user.get();
            String subject = "Felicitări pentru câștig!";
            String content = "Felicitări! Ai câștigat un premiu pentru punctajul tău de " + foundUser.getPoints() + ".";
            emailService.sendEmail(email, subject, content);

            return ResponseEntity.ok().body("Emailul de felicitare a fost trimis către " + email);
        } else {
            return ResponseEntity.ok().body("Nu îndeplinești punctajul minim pentru a primi un premiu.");
        }
    }
}
