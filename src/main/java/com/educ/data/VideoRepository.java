package com.educ.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educ.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long>{

}
