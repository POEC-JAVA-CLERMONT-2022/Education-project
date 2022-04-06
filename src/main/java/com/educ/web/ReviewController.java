package com.educ.web;

import com.educ.data.ReviewRepository;
import com.educ.entity.Modulee;
import com.educ.entity.Review;
import com.educ.entity.User;
import com.educ.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
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

    //check this for add all parameters module & user
    @PostMapping("reviews/add")
    public Review addReview(int note, String comment){
        Review review = new Review(note, comment);
        return reviewRepository.save(review);
    }
}
