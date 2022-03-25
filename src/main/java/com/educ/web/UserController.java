package com.educ.web;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educ.models.User;

import com.educ.services.UserService;

@RestController
public class UserController {
	private UserService userService=new UserService();
	/*
	@GetMapping("/users")
	public ArrayList<User> getUsers(){
		return userService.getAll();
	}
	
	@GetMapping("/users/add")
	public String addUser() {
		userService.addUser(new User("salsabilgrouche@yahoo.fr"));
		//List<UserRole> userRoles=new LinkedList<UserRole>(); 
		//userRoles.add(UserRole.ADMIN);
		userService.addUser(new User("Wendy", "oioioi","Wendy.oioioi@gmail.com" , "Admin reseaux"));
		return "OK";
		
		
	}
*/
}
