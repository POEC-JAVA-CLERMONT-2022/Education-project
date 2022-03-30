package com.educ.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.educ.entity.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long>  {
    //@Query("select b from Book b where b.name = :userName")
    Lesson findByName(String name);

    //Lesson findByNameContainingIgnoreCase(String name);


}
