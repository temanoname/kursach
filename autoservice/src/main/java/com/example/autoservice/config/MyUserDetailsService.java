package com.example.autoservice.config;

import com.example.autoservice.models.Users;
import com.example.autoservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Optional<Users> user = userRepository.findByName(name);

        return  user.map(MyUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(name + "not found!"));
    }
}
