package com.educ.services;

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

	/*private ModuleeService moduleeService;

	private UserService userService;*/

	@Autowired
	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	/*
	@Autowired
	public ReviewService(ReviewRepository reviewRepository, ModuleeService moduleeService, UserService userService) {
		this.reviewRepository = reviewRepository;
		this.moduleeService = moduleeService;
		this.userService = userService;
	}
*/
	public List<Review> findAll(){
		return reviewRepository.findAll();
	}

	public boolean existId(Long id) {
		List<Review> reviews=this.reviewRepository.findAll();
		for(Review review:reviews){
			if(review.getId()==id){
				return true;
			}
		}
		return false;
	}

	public Review getById(Long id) {
		if(this.existId(id)){
			return reviewRepository.getById(id);
		}else{
			return null;
		}
	}

	@Transactional
	//public Review createReview(int note, String comment, String title, String email){
	public Review createReview(int note, String comment){
		Review review=new Review(note,comment);
		//review=this.addReviewModule(review,title);
		//review=this.addReviewUser(review,email);
		this.reviewRepository.save(review);
		return review;
	}

	@Transactional
	public void updateReview (Long id, int note, String comment){
		if(this.existId(id)){
			Review review=this.getById(id);
			review.setNote(note);
			review.setComment(comment);
			this.reviewRepository.save(review);
		}

	}

	@Transactional
	public void deleteReview(Long id){
		Review review=this.getById(id);
		if(review !=null){
			this.reviewRepository.delete(review);
		}
	}
/*
	private Review addReviewModule(Review review, String title){
		try {
			Modulee modulee=moduleeService.findByTitle(title);
			if(review==null){ return null;}
			if(modulee==null) { return review;}
			review.setModule(modulee);
			return review;
		}catch (NullPointerException e){
			System.out.println("Erreur Module title null");
			return review;
		}

	}
	private Review addReviewUser(Review review, String email){
		try {
			User user=userService.findByEmail(email);
			if(review==null){ return null;}
			if(user==null){ return review;}
			review.setUser(user);
			return review;
		}catch (NullPointerException e){
			System.out.println("Erreur user email null");
			return review;
		}

	}*/

}

