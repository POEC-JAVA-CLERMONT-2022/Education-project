package com.educ.web;

import com.educ.data.ReviewRepository;
import com.educ.entity.Modulee;
import com.educ.entity.Review;
import com.educ.entity.User;
import com.educ.services.ReviewService;
import com.educ.services.dto.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//TODO: Gestion des exceptions et gestion des codes de retour
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/reviews")
    public List<Review> getReviews(){
        List<Review> reviews = reviewRepository.findAll();
        return reviews;
    }

    @GetMapping("reviews/{id}")
    public Review getReviewById(@PathVariable Long id){
        Review review = reviewRepository.getById(id);
        return review;
    }

    //check this for insert module & user
    @PostMapping("reviews/add")

    public Review addReview(@RequestBody ReviewDTO reviewDTO){
        String title="JS";
        String email="salsabilgrouche@yahoo.fr";
        Review review=this.reviewService.createReview(reviewDTO.getNote(),reviewDTO.getComment());
        return review;

    }

    //check this for insert module & user
    @PutMapping("reviews/{id}")
    public void updateReview(@PathVariable Long id, @RequestParam int note, @RequestParam String comment){
        reviewService.updateReview(id, note, comment);
    }

    @DeleteMapping("reviews/{id}")
    public void deleteReview(@PathVariable Long id){ reviewRepository.deleteById(id); }



}
