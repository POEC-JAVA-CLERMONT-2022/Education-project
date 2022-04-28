package com.educ.web;

import com.educ.data.ReviewRepository;
import com.educ.entity.Modulee;
import com.educ.entity.Review;
import com.educ.entity.User;
import com.educ.services.ModuleeService;
import com.educ.services.ReviewService;
import com.educ.services.UserService;
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
    private UserService userService;
    private ModuleeService moduleeService;
    @Autowired
    public ReviewController(ReviewService reviewService, UserService userService, ModuleeService moduleeService) {
        this.reviewService = reviewService;
        this.userService = userService;
        this.moduleeService = moduleeService;
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

    @GetMapping("reviews/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Long id){
        try {
            logger.info("Given video {}",id);
            ReviewDTO reviewDTO = new ReviewDTO();
            reviewDTO.convertTo(reviewService.getById(id));
            return new ResponseEntity<>(reviewDTO, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Review not found");
        }
    }

    @PostMapping("reviews/add") /* check for user and module */
    public ResponseEntity<ReviewDTO> addReview(@RequestBody ReviewDTO reviewDTO){
        try {
            ReviewDTO newReview = new ReviewDTO();
            User user=userService.getById(1L);
            Modulee modulee=moduleeService.getById(1L);
            newReview.convertTo(reviewService.createReview(reviewDTO.getNote(),reviewDTO.getComment(),user,modulee));
            return new ResponseEntity<>(newReview, HttpStatus.CREATED);
            //String title="JS";
            //String email="salsabilgrouche@yahoo.fr";
            //Review review=this.reviewService.createReview(reviewDTO.getNote(),reviewDTO.getComment());
            //return review;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to create review");
        }
    }

    //check this for insert module & user
    @PutMapping("reviews/{id}")
    public void updateReview(@PathVariable Long id, @RequestBody ReviewDTO reviewDTO){
        try {
            reviewService.updateReview(id, reviewDTO.getNote(), reviewDTO.getComment());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to update review");
        }
    }

    @DeleteMapping("reviews/{id}")
    public void deleteReview(@PathVariable Long id){
        reviewService.deleteReview(id);
    }
}
