package com.example.educ.services;



import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.example.educ.data.DataProviderUser;
import com.example.educ.data.InMemoryDataProviderUser;
import com.example.educ.models.User;
import com.example.educ.models.UserRole;


public class UserService {
	private DataProviderUser dataProviderUser;
	private List<UserRole> userRoles;
	

	public UserService() {
		
		dataProviderUser=new InMemoryDataProviderUser();
	}
	
	public ArrayList<User> getAll(){
		return dataProviderUser.getAll();
	}
	
	public void addUser(User user) {
		if (user==null || user.getEmail().isEmpty() || user.getEmail()==null) {
			throw new IllegalArgumentException("User or email es invalid");
		}
		dataProviderUser.addUser(user);
	}
/*
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
