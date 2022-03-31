package com.educ.data;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.educ.entity.Role;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public List<Role> findAll();

    public Role getById(Long id);

    @Query("select r from Role r where r.name= :name")
    Role findByName(@Param("name")String name);

}
