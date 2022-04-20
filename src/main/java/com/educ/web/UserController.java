package com.educ.web;
import java.time.LocalDate;
import java.util.List;
import com.educ.data.UserRepository;
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

	//@GetMapping("/users/add")
	//public String addUser() {
		//userService.addUser(new User("salsabilgrouche@yahoo.fr"));
		//List<UserRole> userRoles=new LinkedList<UserRole>(); 
		//userRoles.add(UserRole.ADMIN);
		//return "OK";
	//}

	@PostMapping("users/add")
	@ResponseBody
	public UserDTO addUser(@RequestBody UserDTO userDTO){
		User user = userService.createUser(userDTO);
		UserDTO userDTO1 = new UserDTO(user);
		return userDTO1;
	}

	@PutMapping("users/{id}")
	public void updateUser(@PathVariable Long id, UserDTO userDTO) {
		userService.updateUser(id, userDTO);
	}

	@DeleteMapping("users/{id}")
	//@ResponseBody
	public void deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
	}
}
