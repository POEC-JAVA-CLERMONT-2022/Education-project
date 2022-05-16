package com.educ.data;

import com.educ.entity.Role;
import com.educ.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.educ.entity.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    public List<Review> findAll();

    //public Review getById(Long id);

    /*@EntityGraph(attributePaths = {"eventFiles", "eventFiles.eventFilePK.file"})
    Page<Event> findByTo(User principal, Pageable pageable);*/

    @Query(value = "SELECT * FROM reviews WHERE user_id = :userId AND module_id= :moduleId", nativeQuery = true)
    public Review findByUserAndModule(@Param("userId") Long userId, @Param("moduleId") Long moduleId);

   /* @Query(value = "SELECT r.user_id FROM reviews r WHERE id = :id", nativeQuery = true)
    public Long findUserIdById(Long id);*/

}
