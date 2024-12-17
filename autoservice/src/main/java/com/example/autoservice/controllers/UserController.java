package com.example.autoservice.controllers;

import com.example.autoservice.models.Users;
import com.example.autoservice.services.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String allUsers(Model model) {
        model.addAttribute("users", usersService.findAll());
        return "users";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new Users());
        return "add-user";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") Users user) {
        usersService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Users> user = usersService.findUserById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "edit-user";
        }
        return "redirect:/users";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, @ModelAttribute("user") Users user) {
        user.setId(id);
        usersService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        usersService.deleteUser(id);
        return "redirect:/users";
    }
}
