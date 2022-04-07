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

    @Query("select l from Lesson l where l.name = :name and l.level= :level and l.language= :language")
    Lesson findByNameAndLevelAndLanguage(@Param("name")String name, @Param("level") Level level, @Param("language") Language language);

    //@Query("select l from Lesson l join  l.modules m where l.id = :id")
    //public List<Lesson> getLessonAndModulesById();

}
