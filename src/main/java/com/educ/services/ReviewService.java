package com.educ.services;

import com.educ.data.ModuleeRepository;
import com.educ.data.UserRepository;
import com.educ.entity.Modulee;
import com.educ.entity.Review;
import com.educ.entity.Role;
import com.educ.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educ.data.ReviewRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class ReviewService {

    private ReviewRepository reviewRepository;
    private UserRepository userRepository;
    private ModuleeRepository moduleeRepository;
    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, ModuleeRepository moduleeRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.moduleeRepository = moduleeRepository;
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public boolean existId(Long id) {
        List<Review> reviews = this.reviewRepository.findAll();
        for (Review review : reviews) {
            if (review.getId() == id) {
                return true;
            }
        }
        return false;
    }

   public Review getById(Long id) {
           if (this.existId(id)) {
                try {
                    Review review=reviewRepository.getById(id);
                    return review;
                }
                catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }


            } else {
                return null;
            }




    }

   @Transactional
    public Review createReview(int note, String comment, Long userId, Long moduleeId) {
       Review review;
        try {
            review=this.reviewRepository.findByUserAndModule(userId,moduleeId);
        }catch (Exception e){
            review=null;
        }

      if (review != null){ return review;}
       User user=this.userRepository.getById(userId);
       Modulee modulee=this.moduleeRepository.getById(moduleeId);
      review=new Review(note, comment);
      review.setUser(user);
      review.setModule(modulee);
      review=this.reviewRepository.save(review);
       //Ajout de la review dans user et Module

      /*user=this.addUserReview(user,review.getId());
      modulee=this.addModuleeReview(modulee,review.getId());
      user=this.userRepository.save(user);
      modulee=this.moduleeRepository.save(modulee);*/
      return review;
    }


   /* @Transactional
    public Review createReview(int note, String comment) {
        Review review = new Review(note, comment);
        this.reviewRepository.save(review);
        return review;
    } */

    @Transactional
    public void updateReview(Long id, int note, String comment) {
        if (this.existId(id)) {
            Review review = this.getById(id);
            review.setNote(note);
            review.setComment(comment);
            this.reviewRepository.save(review);
        }

    }

    @Transactional
    public void deleteReview(Long id) {
        Review review = this.getById(id);
        if (review != null) {
            this.reviewRepository.delete(review);
        }
    }

   /* private User addUserReview(User user, Long id){
        try {
            List<Review> reviews;
            Review review=this.getById(id);
            if(user==null){ return null;}
            if(review==null){ return user;}
            reviews=user.getReviews();
            reviews.add(review);
            user.setReviews(reviews);
            return user;
        }catch (NullPointerException e) {
            System.out.println("Erreur relation user Review null");
            return user;
        }

    }*/
/*
    private Modulee addModuleeReview(Modulee modulee, Long id) {
        try {
            List<Review> reviews;
            Review review = this.getById(id);
            if (modulee == null) {
                return null;
            }
            if (review == null) {
                return modulee;
            }
            reviews = modulee.getReviews();
            reviews.add(review);
            modulee.setReviews(reviews);
            return modulee;
        } catch (NullPointerException e) {
            System.out.println("Erreur relation Module Review null");
            return modulee;
        }

    }

 */


}

