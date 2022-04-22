package com.educ.web;
import java.util.LinkedList;
import java.util.List;

import com.educ.services.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.educ.entity.User;
import com.educ.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService){
		this.userService = userService;
	}

	@GetMapping("/users")
	public List<UserDTO> getUsers(){
		List<User> users = userService.findAll();
		List<UserDTO> userDTOS=new LinkedList<UserDTO>();
		for (User user:users){
			UserDTO userDTO=new UserDTO();
			userDTOS.add(userDTO.copyUser(user));
		}
		return userDTOS;
	}

	@GetMapping("{id}")
	public UserDTO getUserById(@PathVariable Long id){
		UserDTO userDTO = new UserDTO();
		userDTO.copyUser(userService.getById(id));
		return userDTO;
	}
/*
	public CookDTO getCookById(@PathVariable int id)
	{
		CookDTO cookDTO = new CookDTO();
		cookDTO.setCookDTO(cookService.getCookById(id));
		return cookDTO;
	}

 */

	/*
	@PostMapping("add")
	public UserDTO addUser(@RequestBody UserDTO userDTO){
		//UserDTO userDTO = new UserDTO();
		User userRequest = BeanUtils.copyProperties(user, userDTO);
		User user = userService.createUser(userDTO);
		//UserDTO userDTO1=new UserDTO(user.getFirstName(), user.getLastName(), user.getBirthAt(), user.getUrlImage(), user.getEmail(), user.getPassword(), user.getStatus());
		return newUser;
	}

	 */


	@PutMapping("{id}")
	public void updateUser(@PathVariable Long id, UserDTO userDTO) { /* not working with @RequestBody */
		userService.updateUser(id, userDTO);
	}

	@DeleteMapping("{id}")
	//@ResponseBody
	public void deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
	}
}
