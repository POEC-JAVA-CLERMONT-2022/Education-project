package com.educ.data;

import com.educ.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.educ.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	//public User findByEMail(String email);

	public List<User> findAll();




	@Modifying
	@Query("update User u set u.firstName = ?1 , u.lastName = ?2 where u.email = ?3")
	User updateFirstNameLastNameByEmail(String firstName, String lastName, String email);

	@Query("select u from User u where u.email= :email")
	User findByEMail(@Param("email")String email);
	
	
}
