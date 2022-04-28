package com.educ.data;

import com.educ.entity.Role;
import com.educ.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.educ.entity.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    public List<Review> findAll();

    public Review getById(Long id);

    @Query(value = "SELECT * FROM reviews WHERE user_id = ?0 AND module_id= ?1", nativeQuery = true)
    public Review findByUserAndModule(Long userId, Long moduleId);

}
