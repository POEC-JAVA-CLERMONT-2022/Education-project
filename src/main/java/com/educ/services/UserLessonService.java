package com.educ.services;

import com.educ.data.UserLessonRepository;
import com.educ.entity.Lesson;
import com.educ.entity.User;
import com.educ.entity.UserLesson;
import com.educ.entity.UserLessonPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class UserLessonService {
    private UserLessonRepository userLessonRepository;

    private LessonService lessonService;

    private UserService userService;

    @Autowired
    public UserLessonService(UserLessonRepository userLessonRepository, LessonService lessonService, UserService userService) {
        this.userLessonRepository = userLessonRepository;
        this.lessonService = lessonService;
        this.userService = userService;
    }

    public List<UserLesson> findByUserLessonPK_user_Id(Long userId){
        List<UserLesson> userLessons=this.userLessonRepository.findByUserLessonPK_user_Id(userId);
        return userLessons;
    }

    public List<UserLesson> findByUserLessonPK_lesson_Id(Long lessonId){
        List<UserLesson> userLessons=this.userLessonRepository.findByUserLessonPK_lesson_Id(lessonId);
        return userLessons;
    }



}
