package com.educ.web;
import java.util.List;

import com.educ.services.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.educ.entity.User;
import com.educ.services.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> getUsers(){
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
		User user = userService.createUser(userDTO);
		return user;
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
