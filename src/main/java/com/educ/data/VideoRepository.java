package com.educ.data;

import com.educ.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.educ.entity.Video;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>{
    public List<Video> findAll();

    public Video getById(Long id);

    @Query("select v from Video v where v.url= :url")
    Video findByUrl(@Param("url")String url);


}
