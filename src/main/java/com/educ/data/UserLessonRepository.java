package com.educ.data;

import com.educ.entity.UserLesson;
import com.educ.entity.UserLessonPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserLessonRepository extends JpaRepository<UserLesson, UserLessonPK> {

    List<UserLesson> findByUserLessonPK_user_Id(Long userId);

    List<UserLesson> findByUserLessonPK_lesson_Id(Long lessonId);

    UserLesson findByUserLessonPK_user_idAndUserLessonPK_lesson_id(Long userId, Long lessonId);

}