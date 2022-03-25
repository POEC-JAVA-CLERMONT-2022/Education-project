package com.educ.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educ.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	//User findByEMail(String email);
	
}
