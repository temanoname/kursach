package com.example.autoservice.repository;

import com.example.autoservice.models.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Services, Long> {
}

