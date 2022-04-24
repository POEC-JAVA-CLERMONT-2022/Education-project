package com.educ.web;
import com.educ.entity.Lesson;
import com.educ.services.LessonService;
import com.educ.services.dto.LessonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/lessons")
public class LessonController {

    private LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService){
        this.lessonService = lessonService;
    }

    @GetMapping("{id}")
    public ResponseEntity<LessonDTO> getLessonById(@PathVariable Long id){
        LessonDTO findLesson = new LessonDTO();
        findLesson.convertTo(lessonService.getById(id));
        return new ResponseEntity<>(findLesson, HttpStatus.OK);
    }


    @PostMapping("add") /* Salsabil check this */
    public ResponseEntity<LessonDTO> addLesson(@RequestBody LessonDTO lessonDTO){
        LessonDTO newLesson = new LessonDTO();
        newLesson.convertTo(lessonService.createLesson(lessonDTO.getName(), lessonDTO.getDescription(), lessonDTO.getPrice(), lessonDTO.getLanguage(), lessonDTO.getLevel()));
        return new ResponseEntity<>(newLesson, HttpStatus.CREATED);
    }

    //Postman language & level : FR / MIDDLE
    //Postman price 150.20
    @PutMapping("{id}")
    public void updateLesson(@PathVariable Long id, @RequestBody LessonDTO lessonDTO){
        lessonService.updateLesson(id, lessonDTO.getName(), lessonDTO.getDescription(), lessonDTO.getPrice(), lessonDTO.getLanguage(), lessonDTO.getLevel());

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Lesson> deleteLesson(@PathVariable Long id){
        lessonService.deleteLesson(id);
        return ResponseEntity.ok().build();
    }

}