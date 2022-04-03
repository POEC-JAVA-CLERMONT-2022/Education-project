package com.educ.services;



import java.time.LocalDate;
import java.util.List;

import com.educ.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.educ.data.UserRepository;
import com.educ.entity.User;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public List<User> findAll(){
		return userRepository.findAll();
	}

	@Transactional(readOnly = true)
	public User findByEMail(String email) {
		return userRepository.findByEMail(email);
	}


	@Transactional(readOnly = true)
	public User getById(Long id) {	return userRepository.getById(id); }



	@Transactional
	public User createUser(String firstName, String lastName, LocalDate birthAt, String urlImage, String email, String password, String status) {
		if(this.findByEMail(email)==null){
			User user=new User(firstName, lastName, birthAt, urlImage, email, password, status);
			this.userRepository.save(user);
			return user;
		}else {
			return null;
		}
	}

	@Transactional
	User updateFirstNameLastNameByEmail(String firstName, String lastName, String email){
		return this.userRepository.updateFirstNameLastNameByEmail(firstName,lastName,email);
	}

	@Transactional
	public void updateUser(Long id, String firstName, String lastName, LocalDate birthAt, String urlImage, String email, String password, String status) {
		if ((this.userRepository.getById(id) != null) &&  (this.userRepository.findByEMail(email)==null)){
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


	@Transactional
	public void deleteUser(Long id){
		User user=this.userRepository.getById(id);
		if(user !=null){
			this.userRepository.delete(user);
		}
	}


	/*public void addUser(User user) {
		if (user==null || user.getEmail().isEmpty() || user.getEmail()==null) {
			throw new IllegalArgumentException("User or email es invalid");
		}
		dataProviderUser.addUser(user);
	}

	public User searchUser(String email) {

		for(User u: users) {
			if(u.getEmail().equals(email)) {
				return u;
			}
		}
		return null;
	}

	public void addUser(String firstName, String lastName, String email, String status, List<UserRole> userRoles) {
		if(this.searchUser(email)==null) {
			User user=new User(firstName, lastName, email, status, userRoles);
			this.users.add(user);
			
		}else {
			//user existe deja
			
		}
	}

	private void addUserRole(String email, UserRole userRole) {
		User user=this.searchUser(email);
		if(user!=null) {
			this.userRoles=user.getUserRoles();
			this.userRoles.add(userRole);
			user.setUserRoles(userRoles);
			this.modifUser(user);
		}
	}

	public void modifUser(User user) {
		User u=this.searchUser(user.getEmail());
		if(u!=null) {
			this.users.remove(u);
			this.users.add(user);
		}
	}

	public void deleteUser(User user) {

		User u=this.searchUser(user.getEmail());
		if(u!=null) {
			this.users.remove(u);
		}
	}
	
	
	*/



}
