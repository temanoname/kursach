package com.example.autoservice.controllers;

import com.example.autoservice.config.MyUserDetails;
import com.example.autoservice.enums.Role;
import com.example.autoservice.models.Reviews;
import com.example.autoservice.models.Users;
import com.example.autoservice.services.ReviewsService;
import com.example.autoservice.services.UsersService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/reviews")
public class ReviewsController {

    private final ReviewsService reviewsService;
    private final UsersService usersService;

    public ReviewsController(ReviewsService reviewsService, UsersService usersService) {
        this.reviewsService = reviewsService;
        this.usersService = usersService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String allReviews(Model model) {
            boolean isAdmin = false;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.getPrincipal() instanceof MyUserDetails) {
                MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();


                isAdmin = myUserDetails.getUser().getRole().equals(Role.ADMIN);

            }
            model.addAttribute("reviews", reviewsService.findAll());
            model.addAttribute("isAdmin", isAdmin);

        return "reviews";
    }

    @GetMapping("/add")
    public String showAddReviewForm(Model model) {
        model.addAttribute("review", new Reviews());
        model.addAttribute("users", usersService.findAll());
        return "add-review";
    }

    @PostMapping("/add")
    public String addReview(@ModelAttribute("review") Reviews review) {
        review.setReview_date(new Date());
        reviewsService.saveReview(review);
        return "redirect:/reviews";
    }

    @GetMapping("/edit/{id}")
    public String showEditReviewForm(@PathVariable("id") Long id, Model model) {
        Optional<Reviews> review = reviewsService.findReviewById(id);
        if (review.isPresent()) {
            model.addAttribute("review", review.get());
            model.addAttribute("users", usersService.findAll());
            return "edit-review";
        }
        return "redirect:/reviews";
    }

    @PostMapping("/edit/{id}")
    public String editReview(@PathVariable("id") Long id, @ModelAttribute("review") Reviews review) {
        Optional<Reviews> existingReview = reviewsService.findReviewById(id);
        if (existingReview.isPresent()) {
            Reviews originalReview = existingReview.get();
            review.setUsers(originalReview.getUsers());
            review.setReview_date(new Date());
            reviewsService.saveReview(review);
        }
        return "redirect:/reviews";
    }

    @GetMapping("/delete/{id}")
    public String deleteReview(@PathVariable("id") Long id) {
        reviewsService.deleteReview(id);
        return "redirect:/reviews";
    }
}
