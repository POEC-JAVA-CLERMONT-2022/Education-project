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
    public ResponseEntity<List<LessonDTO>> getLessons(){
        try {
            List<Lesson> lessons = lessonService.findAll();
            List<LessonDTO> lessonDTOS = new LinkedList<LessonDTO>();
            for(Lesson lesson:lessons){
                LessonDTO lessonDTO = new LessonDTO();
                lessonDTOS.add(lessonDTO.convertTo(lesson));
            }
            return new ResponseEntity<>(lessonDTOS, HttpStatus.OK);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lessons not found");
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<LessonDTO> getLessonById(@PathVariable Long id){
        try {
            logger.info("Given lesson {}",id);
            LessonDTO findLesson = new LessonDTO();
            findLesson.convertTo(lessonService.getById(id));
            return new ResponseEntity<>(findLesson, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found");
        }
    }

    @PostMapping("add")
    public ResponseEntity<LessonDTO> addLesson(@RequestBody LessonDTO lessonDTO){
        try {
            LessonDTO newLesson = new LessonDTO();
            newLesson.convertTo(lessonService.createLesson(lessonDTO.getName(), lessonDTO.getDescription(), lessonDTO.getPrice(), lessonDTO.getLanguage(), lessonDTO.getLevel()));
            return new ResponseEntity<>(newLesson, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to create lesson", e);
        }
    }

    //Postman language & level : FR / MIDDLE
    //Postman price 150.20
    @PutMapping("{id}") /* check return */
    public ResponseEntity<LessonDTO> updateLesson(@PathVariable Long id, @RequestBody LessonDTO lessonDTO){
        try {
            lessonService.updateLesson(id, lessonDTO.getName(), lessonDTO.getDescription(), lessonDTO.getPrice(), lessonDTO.getLanguage(), lessonDTO.getLevel());
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to update lesson");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Lesson> deleteLesson(@PathVariable Long id){
        try {
            lessonService.deleteLesson(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lesson not found");
        }
    }

}