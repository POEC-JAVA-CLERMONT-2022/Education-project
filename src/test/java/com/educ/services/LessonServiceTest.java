package com.educ.services;

import com.educ.data.LessonRepository;
import com.educ.entity.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("test du video service")
public class LessonServiceTest {
    @Autowired
    LessonService lessonService;

    @Autowired
    LessonRepository lessonRepository;

    @BeforeAll
    static void initAll() {
        System.out.println("beforeAll");
    }

    @Test
    @DisplayName("creation lesson test")
    public void testCreationLesson(){
        //create data
        String name="Programmation Oriente objet";
        String description="MVC";
        Float price=12.4f;
        Language language= Language.FR;
        Level level=Level.BEGINNER;
        Lesson lesson=this.lessonService.createLesson(name, description, price, language, level);
        List<Lesson> lessons=lessonService.findAll();
        assertNotNull(lesson);
        assertTrue(lesson.getName().equals("Programmation Oriente objet") );
        assertNotNull(lessons);
        assertEquals(lessons.size(),1);
    }

    @Test
    @DisplayName("update lesson test")
    public void testUpdateLesson(){
        String name="Programmation Oriente objet";
        String description="MVC";
        Float price=12.4f;
        Language language= Language.FR;
        Level l=Level.BEGINNER;
        Lesson lesson=this.lessonService.createLesson(name, description, price, language, l);
        List<Lesson> lessons=lessonService.findAll();
        language = Language.EN;
        l = Level.ADVANCE;

        this.lessonService.updateLesson(1L, name, description, price, language, l);

        List<Lesson> lessonsUpdated=this.lessonService.findAll();

        assertEquals(lessons.size(),lessonsUpdated.size());
        assertTrue(lessonsUpdated.get(0).getLanguage().equals(language) && lessonsUpdated.get(0).getLevel().equals(l)) ;

        this.lessonService.updateLesson(9L,"POO", description, price, language, l);
        lessons=this.lessonService.findAll();
        assertTrue(lessons.size()==1 && lessons.get(0).getId()!=9L);
    }

    @Test
    @DisplayName("delete lesson test")
    public void testDeleteLesson(){
        String name="Programmation Oriente objet";
        String description="MVC";
        Float price=12.4f;
        Language language= Language.FR;
        Level level=Level.BEGINNER;
        Lesson lesson=this.lessonService.createLesson(name, description, price, language, level);
        List<Lesson> lessons=lessonService.findAll();
        assertTrue(lessons.contains(lesson));
        this.lessonService.deleteLesson(1L);
        lessons=lessonService.findAll();
        assertEquals(lessons.size(),0);
        assertFalse(lessons.contains(lesson));
        lesson=this.lessonService.createLesson(name, description, price, language, level);
        lessons=this.lessonService.findAll();
        this.lessonService.deleteLesson(10L);
        lessons=this.lessonService.findAll();
        assertTrue(lessons.contains(lesson));


    }



}
