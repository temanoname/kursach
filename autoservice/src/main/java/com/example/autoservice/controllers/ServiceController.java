package com.example.autoservice.controllers;

import com.example.autoservice.config.MyUserDetails;
import com.example.autoservice.enums.Role;
import com.example.autoservice.models.Services;
import com.example.autoservice.services.ServicesService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/services")
public class ServiceController {

    private final ServicesService servicesService;

    public ServiceController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }


    @GetMapping
    public String allServices(Model model){
        boolean isAdmin = false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof MyUserDetails) {
            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();


            isAdmin = myUserDetails.getUser().getRole().equals(Role.ADMIN);

        }
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("services", servicesService.findAll());
        return "services";
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("service", new Services());
        return "add-service";
    }

    @PostMapping("/add")
    public String addService(@ModelAttribute("service") Services service) {
        servicesService.saveService(service);
        return "redirect:/services";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<Services> service = servicesService.findServiceById(id);
        if (service.isPresent()) {
            model.addAttribute("service", service.get());
            return "edit-service";
        }
        return "redirect:/services";
    }

    @PostMapping("/edit/{id}")
    public String editService(@PathVariable("id") Long id, @ModelAttribute("service") Services service) {
        service.setId(id);
        servicesService.updateService(id, service);
        return "redirect:/services";
    }

    @GetMapping("/delete/{id}")
    public String deleteService(@PathVariable("id") Long id) {
        servicesService.deleteService(id);
        return "redirect:/services";
    }
}
