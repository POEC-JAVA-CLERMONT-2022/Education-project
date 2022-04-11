package com.educ.services;

import com.educ.entity.Review;
import com.educ.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educ.data.ReviewRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class ReviewService {
	@Autowired
	private ReviewRepository reviewRepository;


	@Transactional(readOnly = true)
	public List<Review> findAll(){
		return reviewRepository.findAll();
	}

	@Transactional(readOnly = true)
	public boolean existId(Long id) {
		List<Review> reviews=this.reviewRepository.findAll();
		for(Review review:reviews){
			if(review.getId()==id){
				return true;
			}
		}
		return false;
	}

	@Transactional(readOnly = true)
	public Review getById(Long id) {
		if(this.existId(id)){
			return reviewRepository.getById(id);
		}else{
			return null;
		}
	}

	@Transactional
	public Review createReview(int note, String comment){
		Review review=new Review(note,comment);
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
	
	

}

