package com.example.autoservice.services;

import com.example.autoservice.models.Reviews;
import com.example.autoservice.repository.ReviewRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewsService {

    private final ReviewRepository reviewsRepository;

    public ReviewsService(ReviewRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    public List<Reviews> findAll() {
        return reviewsRepository.findAll();
    }

    public Optional<Reviews> findReviewById(Long id) {
        return reviewsRepository.findById(id);
    }

    public void saveReview(Reviews review) {
        reviewsRepository.save(review);
    }

    public void deleteReview(Long id) {
        reviewsRepository.deleteById(id);
    }
}
