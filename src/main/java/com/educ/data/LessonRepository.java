package com.educ.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educ.entity.Lesson;
@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long>  {

}
