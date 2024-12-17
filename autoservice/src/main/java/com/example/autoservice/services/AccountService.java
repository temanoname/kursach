package com.example.autoservice.services;
import com.example.autoservice.config.SecurityConfig;
import com.example.autoservice.enums.Role;
import com.example.autoservice.models.Users;
import com.example.autoservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AccountService {

    UserRepository userRepository;

    SecurityConfig securityConfig;



    public Users registerUser(Users user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            ResponseEntity.badRequest().body("Неверный запрос");
        }

        if (userRepository.findByName(user.getName()).isPresent()) {
            ResponseEntity.badRequest().body("Неверный запрос");
        }

        user.setRole(Role.USER);
        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);

        return user;
    }

}