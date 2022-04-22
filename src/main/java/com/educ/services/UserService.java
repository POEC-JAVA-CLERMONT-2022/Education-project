package com.educ.services;



import java.time.LocalDate;
import java.util.List;

import com.educ.entity.Role;
import com.educ.services.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.educ.data.UserRepository;
import com.educ.entity.User;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

	private UserRepository userRepository;


	private RoleService roleService;

	@Autowired
	public UserService(UserRepository userRepository, RoleService roleService) {
		this.userRepository = userRepository;
		this.roleService = roleService;
	}

	public List<User> findAll(){
		return userRepository.findAll();
	}

	public User findByEmail(String email) {
		if(email == null){ return null;	}
		return this.userRepository.findByEmail(email);
	}

	public boolean existId(Long id) {
		List<User> users=this.userRepository.findAll();
		for(User user:users){
			if(user.getId()==id){
				return true;
			}
		}
		return false;
	}

	public User getById(Long id) {
		if(this.existId(id)){ return userRepository.getById(id);
		}else{ return null; }
	}

	@Transactional
	public User createUser(String firstName, String lastName, LocalDate birthAt, String urlImage, String email, String password, String status) {
		if (email==null){ return null; }
		if (this.findByEmail(email) != null){ return this.findByEmail(email); }
		User user=new User(firstName, lastName, birthAt, urlImage, email, password, status);
		user=this.addUserRole(user,"Member");
		user =this.userRepository.save(user);
		return user;
	}

	@Transactional
	public User updateByEmail(String firstName, String lastName, LocalDate birthAt, String urlImage, String email, String password, String status){
		if (email== null){ return null; }
		if (this.findByEmail(email) ==null){ return null; }
		User user =this.findByEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setBirthAt(birthAt);
		user.setUrlImage(urlImage);
		user.setPassword(password);
		user.setStatus(status);
		return user;
	}

	@Transactional
	public void updateUser(Long id, String firstName, String lastName, LocalDate birthAt, String urlImage, String email, String password, String status) {
		if(email != null && this.existId(id)){
			if ((this.getById(id) != null) &&  (this.findByEmail(email)==null)){
				User user=this.userRepository.getById(id);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setBirthAt(birthAt);
				user.setUrlImage(urlImage);
				user.setEmail(email);
				user.setPassword(password);
				user.setStatus(status);
				this.userRepository.save(user);
			}
		}
	}

	@Transactional
	public void deleteUser(Long id){
		User user=this.getById(id);
		if(user !=null){
			this.userRepository.delete(user);
		}
	}

	//TODO : à supprimer
	//TODO : indentation
	private User addUserRole(User user, String name) {
		List<Role> roles;
		Role role=roleService.findByName(name);
		if(user==null){ return null; }
		if(role==null){	return user; }
		roles=user.getRoles();
		roles.add(role);
		user.setRoles(roles);
		return user;
	}

}
