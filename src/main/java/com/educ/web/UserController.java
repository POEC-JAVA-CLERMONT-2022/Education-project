package com.educ.web;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import com.educ.services.dto.UserDTO;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.educ.entity.User;
import com.educ.services.UserService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);
	private UserService userService;

	@Autowired
	public UserController(UserService userService){
		this.userService = userService;
	}

	@GetMapping()
	public ResponseEntity<List<UserDTO>> getUsers(){
		try {
			List<User> users = userService.findAll();
			List<UserDTO> userDTOS=new LinkedList<UserDTO>();
			for (User user:users){
				UserDTO userDTO=new UserDTO();
				userDTOS.add(userDTO.copyUser(user));
			}
			return new ResponseEntity<>(userDTOS, HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
		try {
			logger.info("User : {}", id);
			UserDTO userDTO = new UserDTO();
			userDTO.copyUser(userService.getById(id));
			return new ResponseEntity<> (userDTO, HttpStatus.OK);
		} catch (Exception e) {
			/*e.printStackTrace();*/ /* error en console */
			//throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found :", e); /* Error long in pageweb */
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
	}

	@PostMapping("add")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
		//logger.debug("REST request to save User : {}", userDTO);
		try{
			UserDTO createdUser = new UserDTO();
			User user = userService.createUser(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getBirthAt(), userDTO.getUrlImage(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getStatus());
			createdUser.copyUser(user);
			return new ResponseEntity(createdUser, HttpStatus.CREATED); /* OK*/
		} catch (Exception e) { /* check when email exist */
			e.printStackTrace();
			//return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to create user", e);
		}

	}

	@PutMapping()
	public ResponseEntity<UserDTO> updateUserByEmail(@RequestBody UserDTO userDTO) {

		try {
			UserDTO userDTOLocale = new UserDTO();
			LocalDate localDate=userDTO.getBirthAt();
			User user=userService.updateByEmail(userDTO.getFirstName(), userDTO.getLastName(), localDate, userDTO.getUrlImage(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getStatus());
			userDTOLocale.copyUser(user);
			return new ResponseEntity(userDTOLocale, HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to create user", e);
			//return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("{id}")
	public void updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		try {
			logger.info("User : {}", id);
			//UserDTO userDTOLocale = new UserDTO();
			LocalDate localDate=userDTO.getBirthAt();
			userService.updateUser(id,userDTO.getFirstName(), userDTO.getLastName(), localDate, userDTO.getUrlImage(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getStatus());
		} catch (Exception e) {
			logger.info("User : {}", id);
			e.printStackTrace();
		}

	}

	@DeleteMapping("{id}")
	public void deleteUser(@PathVariable Long id){
		try {
			userService.deleteUser(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}