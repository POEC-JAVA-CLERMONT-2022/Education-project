package com.educ.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.educ.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	//public User findByEMail(String email);
	@Modifying
	@Query("update User u set u.firstName = ?1 , u.lastName = ?2 where u.email = ?3")
	int updateFirstNameLastName(String firstName, String lastName, String email);
	
	
	
	
}
