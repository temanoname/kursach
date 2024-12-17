package com.example.autoservice.repository;

import com.example.autoservice.models.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Reviews, Long> {
}
