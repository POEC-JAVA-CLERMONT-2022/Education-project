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

	public List<User> findAll();

	/*@Modifying
	@Query("update User u set u.firstName = :firstName, u.lastName = :lastName where u.email = :email")
	User updateFirstNameLastNameByEmail(String firstName, String lastName, String email);*/


	/* @Query("select u from User u where u.email= :email") */
	User findByEmail(String email);

}
