package com.florina.greenpanion.repository;

import com.florina.greenpanion.model.Plastic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlasticRepository extends JpaRepository<Plastic, Long> {
}
