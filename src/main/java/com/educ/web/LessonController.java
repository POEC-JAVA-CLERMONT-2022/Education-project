package com.educ.web;

import com.educ.data.LessonRepository;
import com.educ.entity.Lesson;
import com.educ.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @Autowired
    private LessonRepository lessonRepository;

    @GetMapping("/lessons/{id}")
    public Lesson getLessonById(@PathVariable Long id){
        Lesson lesson = lessonRepository.getById(id);
        return lesson;
    }
}
