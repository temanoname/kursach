package com.example.autoservice.controllers;

import com.example.autoservice.config.MyUserDetails;
import com.example.autoservice.enums.Role;
import com.example.autoservice.models.Cars;
import com.example.autoservice.models.Orders;
import com.example.autoservice.models.Services;
import com.example.autoservice.services.CarsService;
import com.example.autoservice.services.OrdersService;
import com.example.autoservice.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private ServicesService servicesService;

    @Autowired
    private CarsService carsService;
    private final OrdersService ordersService;

    @Autowired
    public OrderController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String getAllOrders(Model model) {
        boolean isAdmin = false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof MyUserDetails) {
            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();


            isAdmin = myUserDetails.getUser().getRole().equals(Role.ADMIN);

        }
        model.addAttribute("orders", ordersService.findAll());
        model.addAttribute("isAdmin", isAdmin);
        return "/orders";
    }

    @GetMapping("/add")
    public String showAddOrderForm(ModelMap model) {
        List<Services> servicesList = servicesService.findAll();
        List<Cars> carsList = carsService.findAll();

        model.addAttribute("services", servicesList);
        model.addAttribute("cars", carsList);
        model.addAttribute("order", new Orders());

        return "add-order";
    }

    @PostMapping("/add")
    public ModelAndView addOrder(@ModelAttribute Orders order, ModelMap model) {
        ordersService.save(order);  // Сохраняем заказ
        boolean isAdmin = false;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof MyUserDetails) {
            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();


            isAdmin = myUserDetails.getUser().getRole().equals(Role.ADMIN);

        }
        if(isAdmin) {
            return new ModelAndView("redirect:/orders", model);
        }
        else {
            return new ModelAndView("redirect:/main", model);
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        Orders order = ordersService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid order id: " + id));
        ordersService.delete(order);
        return "redirect:/orders";
    }

    @PostMapping("/update-status/{id}")
    public String updateOrderStatus(@PathVariable Long id, @RequestParam("completed") Boolean completed) {
        ordersService.updateOrderStatus(id, completed);
        return "redirect:/orders";
    }

}
