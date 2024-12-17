package com.example.autoservice.repository;

import com.example.autoservice.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByName(String name);
    Optional<Users> findByEmail(String email);

}
