package com.educ.web;
import java.time.LocalDate;
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

	@PostMapping("users/add")
	@ResponseBody
	public User addUser(){
		User user = new User("Clara", "Pariente", LocalDate.parse("1990-03-03"), "/photos/wg.jpg", "clara@gmail.com", "", "admin");
		return userRepository.save(user);
	}

	@GetMapping("users/{id}")
	public User getUserById(@PathVariable Long id){
		User user = userRepository.getById(id);
		return user;
	}

	@PutMapping("users/{id}")
	public User updateUser(@PathVariable Long id, String firstName, String lastName, LocalDate birthAt, String urlImage, String email, String password, String status) {
		User user = userRepository.getById(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setBirthAt(birthAt);
		user.setUrlImage(urlImage);
		user.setEmail(email);
		user.setPassword(password);
		user.setStatus(status);

		this.userRepository.save(user);
		return user;
	}

	@DeleteMapping("users/{id}")
	@ResponseBody
	public void deleteUser(@PathVariable Long id){
		this.userRepository.deleteById(id);
	}
}
