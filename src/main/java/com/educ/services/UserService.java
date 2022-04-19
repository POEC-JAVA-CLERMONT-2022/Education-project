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
		if(email!=null){
			List<User> users=this.findAll();
			for (User user:users){
				if(user.getEmail().equals(email)){
					return user;
				}
			}
			return null;
		}else{
			return null;
		}

	}

	@Transactional(readOnly = true)
	public boolean existId(Long id) {
		List<User> users=this.userRepository.findAll();
		for(User user:users){
			if(user.getId()==id){
				return true;
			}
		}
		return false;
	}


	@Transactional(readOnly = true)
	public User getById(Long id) {
		if(this.existId(id)){
			return userRepository.getById(id);
		}else{
			return null;
		}
	}



	@Transactional
	public User createUser(String firstName, String lastName, LocalDate birthAt, String urlImage, String email, String password, String status) {
		if(email!=null && this.findByEMail(email)==null){
			User user=new User(firstName, lastName, birthAt, urlImage, email, password, status);
			//this.userRepository.save(user);
			return userRepository.save(user);
		}else {
			return null;
		}
	}

	@Transactional
	User updateFirstNameLastNameByEmail(String firstName, String lastName, String email){
		if(email != null && this.findByEMail(email)!=null){
			User user =this.findByEMail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			return user;
		}else{
			return null;
		}
	}

	@Transactional
	public void updateUser(Long id, String firstName, String lastName, LocalDate birthAt, String urlImage, String email, String password, String status) {
		if(email != null && this.existId(id)){
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
	}

	@Transactional
	public void deleteUser(Long id){
		User user=this.getById(id);
		if(user !=null){
			this.userRepository.delete(user);
		}
	}


	/*


	private void addUserRole(String email, UserRole userRole) {
		User user=this.searchUser(email);
		if(user!=null) {
			this.userRoles=user.getUserRoles();
			this.userRoles.add(userRole);
			user.setUserRoles(userRoles);
			this.modifUser(user);
		}
	}


	*/



}
