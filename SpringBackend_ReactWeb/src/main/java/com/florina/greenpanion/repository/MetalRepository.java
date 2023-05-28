package com.florina.greenpanion.repository;


import com.florina.greenpanion.model.Metal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetalRepository extends JpaRepository<Metal, Long> {
}
