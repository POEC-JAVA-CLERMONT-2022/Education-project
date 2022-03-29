package com.educ.services;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.educ.data.UserRepository;
import com.educ.entity.User;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
/*
	public User findByEMail(String email) {
		return userRepository.findByEMail(email);
	}
	*/
	public User findById(Long id) {
		return userRepository.getById(id);
	}
	
	public User create() {
		User user=new User();
		return this.userRepository.save(user);
	}
	
		
	public User create(String firstName, String lastName, String email, String status) {
		User user=new User(firstName, lastName,email, status);
		return this.userRepository.save(user);
	}
	
	public void update(String firstName, String lastName, String email, String status) {
		
		
	}
	/*public void addUser(User user) {
		if (user==null || user.getEmail().isEmpty() || user.getEmail()==null) {
			throw new IllegalArgumentException("User or email es invalid");
		}
		dataProviderUser.addUser(user);
	}

	public User searchUser(String email) {

		for(User u: users) {
			if(u.getEmail().equals(email)) {
				return u;
			}
		}
		return null;
	}

	public void addUser(String firstName, String lastName, String email, String status, List<UserRole> userRoles) {
		if(this.searchUser(email)==null) {
			User user=new User(firstName, lastName, email, status, userRoles);
			this.users.add(user);
			
		}else {
			//user existe deja
			
		}
	}

	private void addUserRole(String email, UserRole userRole) {
		User user=this.searchUser(email);
		if(user!=null) {
			this.userRoles=user.getUserRoles();
			this.userRoles.add(userRole);
			user.setUserRoles(userRoles);
			this.modifUser(user);
		}
	}

	public void modifUser(User user) {
		User u=this.searchUser(user.getEmail());
		if(u!=null) {
			this.users.remove(u);
			this.users.add(user);
		}
	}

	public void deleteUser(User user) {

		User u=this.searchUser(user.getEmail());
		if(u!=null) {
			this.users.remove(u);
		}
	}
	
	
	*/



}
