package com.florina.greenpanion.repository;

import com.florina.greenpanion.model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Long> {
}
