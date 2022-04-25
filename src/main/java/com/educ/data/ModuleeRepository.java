package com.educ.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.educ.entity.Modulee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleeRepository extends JpaRepository<Modulee, Long> 
{

    public List<Modulee> findAll();

    /*@Query("select m from Modulee m where m.title = :title")*/
    Modulee findByTitle(String title);
}
