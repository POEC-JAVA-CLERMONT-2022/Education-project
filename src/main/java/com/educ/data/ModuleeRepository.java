package com.educ.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educ.entity.Modulee;


public interface ModuleeRepository extends JpaRepository<Modulee, Long> 
{

}
