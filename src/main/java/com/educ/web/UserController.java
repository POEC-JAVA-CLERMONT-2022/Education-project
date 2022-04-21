package com.educ.web;
import java.util.List;

import com.educ.services.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.educ.entity.User;
import com.educ.services.UserService;

@RestController
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService){
		this.userService = userService;
	}

	@GetMapping("/users")
	public List<User> getUsers(){ /* ask for findAll DTO*/
		List<User> users = userService.findAll();
		return users;
	}

	@GetMapping("users/{id}")
	public User getUserById(@PathVariable Long id){
		User user = userService.getById(id);
		return user;
	}

	@PostMapping("users/add")
	public User addUser(@RequestBody UserDTO userDTO){
		User newUser = userService.createUser(userDTO);
		//UserDTO userDTO1=new UserDTO(user.getFirstName(), user.getLastName(), user.getBirthAt(), user.getUrlImage(), user.getEmail(), user.getPassword(), user.getStatus());
		return newUser;
	}



	@PutMapping("users/{id}")
	public void updateUser(@PathVariable Long id, UserDTO userDTO) { /* not working with @RequestBody */
		userService.updateUser(id, userDTO);
	}

	@DeleteMapping("users/{id}")
	//@ResponseBody
	public void deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
	}
}
