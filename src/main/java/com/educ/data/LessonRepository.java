package com.educ.data;

import com.educ.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long>  {
    public Lesson getById(Long id);

    public List<Lesson> findAll();

    /* @Query("select l from Lesson l where l.name = :name and l.level= :level and l.language= :language") */
    Lesson findByNameAndLevelAndLanguage(String name, Level level, Language language);

/*
    @Query(value = "SELECT m.id FROM `modules` as m inner join modules_lessons AS ml WHERE ml.modulee_id=m.id AND ml.lessons_id=:lesson_id", nativeQuery = true)
    public List<Long> findListModuleeByLessonId(@Param("lesson_id") Long id);
    */

}
