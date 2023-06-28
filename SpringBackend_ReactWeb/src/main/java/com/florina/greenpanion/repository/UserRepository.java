package com.florina.greenpanion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.florina.greenpanion.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByOrderByPointsDesc();
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
