package com.example.autoservice.controllers;
import com.example.autoservice.models.Users;
import com.example.autoservice.services.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AccountController {

    AccountService accountService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser( Users user, Model model) {
        Users registeredUser = accountService.registerUser(user);
        model.addAttribute("user", registeredUser);
        return "redirect:/login";
    }
}