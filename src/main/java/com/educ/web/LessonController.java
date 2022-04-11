package com.educ.web;

import com.educ.data.LessonRepository;
import com.educ.entity.Language;
import com.educ.entity.Lesson;
import com.educ.entity.Level;
import com.educ.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("lessons/add")
    public Lesson addLesson(@RequestBody Lesson lesson){
        return lessonRepository.save(lesson);

    }
    //Postman language & level : FR / MIDDLE
    //Postman price 150.20
    @PutMapping("lessons/{id}")
    public void updateLesson(@PathVariable Long id, @RequestParam String name,@RequestParam String description, @RequestParam Float price, @RequestParam Language language, @RequestParam Level level){
        lessonService.updateLesson(id, name, description, price, language, level);

    }

    @DeleteMapping("lessons/{id}")
    public void deleteLesson(@PathVariable Long id){ lessonRepository.deleteById(id);}
}
