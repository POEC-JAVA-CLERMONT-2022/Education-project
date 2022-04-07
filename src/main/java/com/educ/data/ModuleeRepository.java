package com.educ.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.educ.entity.Modulee;

import java.util.List;

public interface ModuleeRepository extends JpaRepository<Modulee, Long> 
{

    public List<Modulee> findAll();

    @Query("select m from Modulee m where m.title = :title")
    Modulee findByTitle(@Param("title")String title);
}
