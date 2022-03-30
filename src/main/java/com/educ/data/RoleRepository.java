package com.educ.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.educ.entity.Role;

@Repository

public interface RoleRepository extends JpaRepository<Role, Long> {

    // void findOneRoleName();
}
