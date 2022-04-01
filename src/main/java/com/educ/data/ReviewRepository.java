package com.educ.data;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educ.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    /*
    @EntityGraph(attributePaths = {"item", "reviewer"})
    List<Review> findByScoreAfter(Integer score);

    List<Review> findAll();

     */
}
