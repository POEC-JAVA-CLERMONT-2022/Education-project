package com.educ.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.educ.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.educ.entity.User;
import com.educ.services.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/users")
	public List<User> getUsers(){
		List<User> users = userService.findAll();
		//return (ArrayList<User>) userService.findAll();
		return users;
	}


	//@GetMapping("/users/add")
	//public String addUser() {
		//userService.addUser(new User("salsabilgrouche@yahoo.fr"));
		//List<UserRole> userRoles=new LinkedList<UserRole>(); 
		//userRoles.add(UserRole.ADMIN);
		//return "OK";
	//}

	/*
	@GetMapping("/users/add")
	public String addUser(){
		userService.createUser("Wendy", "Pariente", LocalDate.parse("1990-03-03"), "/photos/wg.jpg", "gg@gmail.com", "", "admin");
		return "OK";
	}*/

	@GetMapping("users/add")
	public User addUser(){
		User user = new User("Wendy", "Pariente", LocalDate.parse("1990-03-03"), "/photos/wg.jpg", "post@gmail.com", "", "admin");
		return userRepository.save(user);
	}

	@GetMapping("users/{id}")
	public User getUserById(@PathVariable Long id){
		User user = userRepository.getById(id);
		return user;
	}
}
