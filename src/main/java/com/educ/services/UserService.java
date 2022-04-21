package com.educ.services;



import java.time.LocalDate;
import java.util.List;

import com.educ.entity.Role;
import com.educ.services.dto.AddUserRoleDTO;
import com.educ.services.dto.UserDTO;
import org.hibernate.result.Output;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.educ.data.UserRepository;
import com.educ.entity.User;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleService roleService;

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
					System.out.println("*******"+user.toString());
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
//	public User createUser(String firstName, String lastName, LocalDate birthAt, String urlImage, String email, String password, String status) {
	public User createUser(UserDTO userDTO) {
		if(userDTO.getEmail()!=null && this.findByEMail(userDTO.getEmail())==null){
			User user=new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getBirthAt(), userDTO.getUrlImage(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getStatus());

			//user = userRepository.save(user);
			//AddUserRoleDTO addUserRoleDTO=new (userDTO.getEmail(), "Member");
			//user=this.addUserRole(addUserRoleDTO);
			user=this.addUserRole(user,"Member");
			//System.out.println("creationUser + role => "+user.toString());
			return user;
			//return userRepository.save(user);
		}else {
			return null;
		}
	}

	@Transactional
	//User updateFirstNameLastNameByEmail(String firstName, String lastName, String email){
	public User updateByEmail(UserDTO userDTO){
		if(userDTO.getEmail() != null && this.findByEMail(userDTO.getEmail())!=null){
			User user =this.findByEMail(userDTO.getEmail());
			user.setFirstName(userDTO.getFirstName());
			user.setLastName(userDTO.getLastName());
			user.setBirthAt(userDTO.getBirthAt());
			user.setUrlImage(userDTO.getUrlImage());
			user.setPassword(userDTO.getPassword());
			user.setStatus(userDTO.getStatus());
			return user;
		}else{
			return null;
		}
	}

	@Transactional
	//public void updateUser(Long id, String firstName, String lastName, LocalDate birthAt, String urlImage, String email, String password, String status) {
	public void updateUser(Long id, UserDTO userDTO) {
		if(userDTO.getEmail() != null && this.existId(id)){
			if ((this.userRepository.getById(id) != null) &&  (this.userRepository.findByEMail(userDTO.getEmail())==null)){
				User user=this.userRepository.getById(id);
				user.setFirstName(userDTO.getFirstName());
				user.setLastName(userDTO.getLastName());
				user.setBirthAt(userDTO.getBirthAt());
				user.setUrlImage(userDTO.getUrlImage());
				user.setEmail(userDTO.getEmail());
				user.setPassword(userDTO.getPassword());
				user.setStatus(userDTO.getStatus());
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
	//private User addUserRole(String email, String name) {
	//private User addUserRole(AddUserRoleDTO addUserRoleDTO) {
		private User addUserRole(User user, String name) {
		List<Role> roles;
		//User user=this.findByEMail(addUserRoleDTO.getMail());
		//Role role=roleService.findByName(addUserRoleDTO.getName());
		Role role=roleService.findByName(name);

		if(user!=null && role!=null) {
			roles=user.getRoles();

			roles.add(role);

			user.setRoles(roles);
			user = userRepository.save(user);

			return user;
		}else {
			if(role==null){
				return user;
			}else{
				return null;
			}
		}
	}

}
