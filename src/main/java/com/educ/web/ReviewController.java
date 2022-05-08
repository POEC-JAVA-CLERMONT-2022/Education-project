package com.educ.web;

import com.educ.entity.Lesson;
import com.educ.entity.Modulee;
import com.educ.entity.Review;

import com.educ.services.ReviewService;

import com.educ.services.dto.ReviewDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/reviews")

public class ReviewController {

    Logger logger = LoggerFactory.getLogger(ReviewController.class);

    private ReviewService reviewService;


    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;

    }

    @GetMapping()
    public ResponseEntity<?> getReviews(){
        try {
            List<Review> reviews = reviewService.findAll();
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getReviewById(@PathVariable Long id){
        try {
            logger.info("Given Review {}",id);
            Review findReview = reviewService.getById(id);
            if(findReview == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(findReview, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
    }

    @PostMapping()
    public ResponseEntity<?> addReview(@RequestBody Review review){
        try {
            Review createdReview = reviewService.createReview(review.getNote(),review.getComment(),review.getUser().getId(), review.getModule().getId());
            return new ResponseEntity<>(createdReview, HttpStatus.CREATED);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to create review");
        }
    }


    @PutMapping("{id}")
    public ResponseEntity<?> updateReview(@PathVariable Long id, @RequestBody Review review){
        try {
            if(id != null){
                reviewService.updateReview(id, review.getNote(), review.getComment());
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to update review");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Long id){
        try {
            Review deletedReview = reviewService.getById(id);
            if(deletedReview == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            reviewService.deleteReview(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found");
        }
    }
}
