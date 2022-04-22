package com.educ.web;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;

import com.educ.services.dto.UserDTO;

import jdk.jfr.DataAmount;
import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

	@GetMapping()
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

	@PostMapping("add")
	public UserDTO addUser(@RequestBody UserDTO userDTO){
		UserDTO userDTOLocale = new UserDTO();
		User user = userService.createUser(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getBirthAt(), userDTO.getUrlImage(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getStatus());
		return userDTOLocale.copyUser(user);
	}

	@PutMapping("")
	public UserDTO updateUserByEmail(@RequestBody UserDTO userDTO) { /* not working with @RequestBody */
		UserDTO userDTOLocale = new UserDTO();
		LocalDate localDate=userDTO.getBirthAt();
		User user=userService.updateByEmail(userDTO.getFirstName(), userDTO.getLastName(), localDate, userDTO.getUrlImage(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getStatus());
		return userDTOLocale.copyUser(user);
	}

	@PutMapping("{id}")
	public void updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) { /* not working with @RequestBody */
		UserDTO userDTOLocale = new UserDTO();
		LocalDate localDate=userDTO.getBirthAt();
		userService.updateUser(id,userDTO.getFirstName(), userDTO.getLastName(), localDate, userDTO.getUrlImage(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getStatus());
	}

	@DeleteMapping("{id}")
	//@ResponseBody
	public void deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
	}
}
