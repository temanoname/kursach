package com.example.autoservice.controllers;

import com.example.autoservice.models.Cars;
import com.example.autoservice.models.Users;
import com.example.autoservice.services.CarsService;
import com.example.autoservice.services.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cars")
public class CarsController {

    private final CarsService carsService;
    private final UsersService usersService;

    public CarsController(CarsService carsService, UsersService usersService) {
        this.carsService = carsService;
        this.usersService = usersService;
    }

    @GetMapping
    public String allCars(Model model) {
        model.addAttribute("cars", carsService.findAll());
        return "cars";
    }

    @GetMapping("/add")
    public String showAddForm(Model model, Principal principal) {
        List<Users> users = usersService.findAll();
        Users currentUser = usersService.findByName(principal.getName());
        model.addAttribute("car", new Cars());
        model.addAttribute("users", users);
        model.addAttribute("currentUser", currentUser);
        return "add-car";
    }

    @PostMapping("/add")
    public String addCar(@ModelAttribute("car") Cars car) {
        carsService.saveCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Cars> car = carsService.findById(id);
        if (car.isPresent()) {
            model.addAttribute("car", car.get());
            model.addAttribute("users", usersService.findAll());
            return "edit-car";
        }
        return "redirect:/cars";
    }

    @PostMapping("/edit/{id}")
    public String editCar(@PathVariable("id") Long id, @ModelAttribute("car") Cars car) {
        carsService.updateCar(id, car);
        return "redirect:/cars";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable("id") Long id) {
        carsService.deleteCar(id);
        return "redirect:/cars";
    }
}

