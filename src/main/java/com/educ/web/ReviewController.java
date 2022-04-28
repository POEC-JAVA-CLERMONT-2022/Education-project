package com.educ.web;

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
    public ResponseEntity<List<ReviewDTO>> getReviews(){
        try {
            List<Review> reviews = reviewService.findAll();
            List<ReviewDTO> reviewDTOS = new LinkedList<ReviewDTO>();
            for (Review review:reviews){
                ReviewDTO reviewDTO =  new ReviewDTO();
                reviewDTOS.add(reviewDTO.convertTo(review));
            }
            return new ResponseEntity<>(reviewDTOS, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id){
        try {
            logger.info("Given video {}",id);
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.convertTo(reviewService.getById(id));
           // reviewDTO.setUser_id(this.reviewService.findUserIdById(id));
            return new ResponseEntity<>(reviewDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
    }

    @PostMapping("add") /* check for user and module */
    public ResponseEntity<ReviewDTO> addReview(@RequestBody ReviewDTO reviewDTO){
        try {
            ReviewDTO newReview = new ReviewDTO();
            newReview.convertTo(reviewService.createReview(reviewDTO.getNote(),reviewDTO.getComment(),reviewDTO.getUser().getId(), reviewDTO.getModulee().getId()));
            return new ResponseEntity<>(newReview, HttpStatus.CREATED);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to create review");
        }
    }

    //check this for insert module & user
    @PutMapping("{id}")
    public void updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO){
        try {
            reviewService.updateReview(id, reviewDTO.getNote(), reviewDTO.getComment());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to update review");
        }
    }

    @DeleteMapping("{id}")
    public void deleteReview(@PathVariable Long id){
        reviewService.deleteReview(id);
    }
}
