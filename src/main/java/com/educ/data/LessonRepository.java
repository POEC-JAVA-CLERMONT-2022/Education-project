package com.educ.data;

import com.educ.entity.Language;
import com.educ.entity.Level;
import com.educ.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.educ.entity.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long>  {
    //@Query("select b from Book b where b.name = :userName")
    public Lesson getById(Long id);

    /* @Query("select l from Lesson l where l.name = :name")
    Lesson findByName(@Param("name")String name);  */

    @Query("select l from Lesson l where l.name = :name and l.level= :level and l.language= :language")
    Lesson findByNameAndLevelAndLanguage(@Param("name")String name, @Param("level") Level level, @Param("language") Language language);

}
