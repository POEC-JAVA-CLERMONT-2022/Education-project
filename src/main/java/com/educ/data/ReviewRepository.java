package com.educ.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educ.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

}
