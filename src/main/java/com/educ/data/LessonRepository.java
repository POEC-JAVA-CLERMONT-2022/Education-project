package com.educ.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educ.models.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long>  {

}
