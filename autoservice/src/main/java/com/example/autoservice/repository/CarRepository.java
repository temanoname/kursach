package com.example.autoservice.repository;

import com.example.autoservice.models.Cars;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CarRepository extends JpaRepository<Cars, Long> {

}
