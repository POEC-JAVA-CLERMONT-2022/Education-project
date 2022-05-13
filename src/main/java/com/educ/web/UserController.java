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
	public ResponseEntity<?> getUsers(){
		try {
			List<User> users = userService.findAll();
			List<UserDTO> userDTOS=new LinkedList<UserDTO>();
			UserDTO userDTO=new UserDTO();
			for(User user:users){
				userDTOS.add(userDTO.copyUser(user));
			}
			return new ResponseEntity<>(userDTOS, HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id){
		try {
			logger.info("User : {}", id);
			User findUser =  userService.getById(id);
			if(findUser == null){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			UserDTO userDTO=new UserDTO();
			return new ResponseEntity<> (userDTO.copyUser(findUser), HttpStatus.OK);
		} catch (Exception e) {
			/*e.printStackTrace();*/ /* error en console */
			//throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found :", e); /* Error long in pageweb */
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
	}

	@PostMapping()
	public ResponseEntity<?> addUser(@RequestBody User user){
		//logger.debug("REST request to save User : {}", userDTO);
		try{
			User createdUser = userService.createUser(user.getFirstName(), user.getLastName(), user.getBirthAt(), user.getUrlImage(), user.getEmail(), user.getPassword(), user.getStatus());
			return new ResponseEntity(createdUser, HttpStatus.CREATED);
		} catch (Exception e) { /* check when email exist */
			e.printStackTrace();
			//return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to create user", e);
		}

	}

	@PutMapping()
	public ResponseEntity<?> updateUserByEmail(@RequestBody User user) {
		try {
			if(user.getEmail() != null){
				LocalDate localDate=user.getBirthAt();
				User updatedUser = userService.updateByEmail(user.getFirstName(), user.getLastName(), localDate, user.getUrlImage(), user.getEmail(), user.getPassword(), user.getStatus());
				return new ResponseEntity(updatedUser, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to create user", e);
			//return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
		try {
			if(id != null){
				logger.info("User : {}", id);
				LocalDate localDate=user.getBirthAt();
				userService.updateUser(id,user.getFirstName(), user.getLastName(), localDate, user.getUrlImage(), user.getEmail(), user.getPassword(), user.getStatus());
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			logger.info("User : {}", id);
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error to update user");
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		try {
			User deletedUser = userService.getById(id);
			if(deletedUser == null){
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			userService.deleteUser(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
	}
}