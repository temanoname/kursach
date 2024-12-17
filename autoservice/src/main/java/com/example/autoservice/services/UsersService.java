package com.example.autoservice.services;

import com.example.autoservice.models.Users;
import com.example.autoservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private final UserRepository usersRepository;

    public UsersService(UserRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public Optional<Users> findUserById(Long id) {
        return usersRepository.findById(id);
    }

    public Users findByName(String name) {
        return usersRepository.findByName(name).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void saveUser(Users user) {
        usersRepository.save(user);
    }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }
}
