package com.educ.web;
import com.educ.entity.Lesson;

import com.educ.services.LessonService;

import com.educ.services.dto.LessonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedList;
import java.util.List;


@RestController
@RequestMapping("/lessons")
public class LessonController {

    Logger logger = LoggerFactory.getLogger(LessonController.class);
    private LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService){
        this.lessonService = lessonService;
    }

    @GetMapping()
    public ResponseEntity<?> getLessons(){
        try {
            List<Lesson> lessons = lessonService.findAll();
            List<LessonDTO> lessonDTOS=new LinkedList<LessonDTO>();
            LessonDTO lessonDTO=new LessonDTO();
            for(Lesson lesson:lessons){
                lessonDTOS.add(lessonDTO.convertTo(lesson));
            }
            return new ResponseEntity<>(lessonDTOS, HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lessons not found");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getLessonById(@PathVariable Long id){
        try {
            logger.info("Given lesson {}",id);
            Lesson findLesson = lessonService.getById(id);
            if(findLesson == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            LessonDTO lessonDTO=new LessonDTO();
            return new ResponseEntity<>(lessonDTO.convertTo(findLesson), HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found");
        }
    }

    @PostMapping()
    public ResponseEntity<?> addLesson(@RequestBody Lesson lesson){
        try {
            if(lesson.getName() == null || lesson.getName() == ""){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            Lesson newLesson = lessonService.createLesson(lesson.getName(), lesson.getDescription(), lesson.getPrice(), lesson.getLanguage(), lesson.getLevel());
            return new ResponseEntity<>(newLesson, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to create lesson", e);
        }
    }

    //Postman language & level : FR / MIDDLE
    //Postman price 150.20
    @PutMapping("{id}")
    public ResponseEntity<?> updateLesson(@PathVariable Long id, @RequestBody Lesson lesson){
        try {
            if(id != null){
                lessonService.updateLesson(id, lesson.getName(), lesson.getDescription(), lesson.getPrice(), lesson.getLanguage(), lesson.getLevel());
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to update lesson");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteLesson(@PathVariable Long id){
        try {
            Lesson lesson = lessonService.getById(id);
            if(lesson == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            lessonService.deleteLesson(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found");
        }
    }

}