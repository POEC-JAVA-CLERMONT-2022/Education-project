package com.educ.web;

import com.educ.entity.Lesson;
import com.educ.entity.Modulee;
import com.educ.entity.Review;

import com.educ.services.ReviewService;

import com.educ.services.dto.CreationReviewDTo;
import com.educ.services.dto.ModuleeDTO;
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
            List<ReviewDTO> reviewDTOS=new LinkedList<ReviewDTO>();
            ReviewDTO reviewDTO=new ReviewDTO();
            for (Review review:reviews){
                reviewDTOS.add(reviewDTO.convertTo(review));
            }
            return new ResponseEntity<>(reviewDTOS, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getReviewById(@PathVariable Long id){
        try {
            logger.info("Given Review {}",id);
            Review findReview = reviewService.getById(id);


            ReviewDTO reviewDTO=new ReviewDTO();
            if(findReview == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(reviewDTO.convertTo(findReview), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
    }

    @PostMapping()
    public ResponseEntity<?> addReview(@RequestBody CreationReviewDTo creationReviewDTo){
        try {
            Review review = reviewService.createReview(creationReviewDTo.getNote(),creationReviewDTo.getComment(),creationReviewDTo.getUser_id(), creationReviewDTo.getModulee_id());
            CreationReviewDTo createdReview=new CreationReviewDTo();
            return new ResponseEntity<>(createdReview.convertTo(review), HttpStatus.CREATED);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to create review");
        }
    }


    @PutMapping("{id}")
    public ResponseEntity<?> updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO){
        try {
            if(id != null){
                reviewService.updateReview(id, reviewDTO.getNote(), reviewDTO.getComment());
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
