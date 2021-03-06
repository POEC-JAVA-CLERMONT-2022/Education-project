package com.educ.services;



import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.educ.data.UserLessonRepository;
import com.educ.entity.*;
import com.educ.services.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.educ.data.UserRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

	private UserRepository userRepository;

	private RoleService roleService;

	private UserLessonRepository userLessonRepository;

	@Autowired
	public UserService(UserRepository userRepository, RoleService roleService, UserLessonRepository userLessonRepository) {
		this.userRepository = userRepository;
		this.roleService = roleService;
		this.userLessonRepository = userLessonRepository;
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
		return userRepository.findById(id).orElseThrow();

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
			User u=this.findByEmail(email);
			if(u==null || u.getId()==id){
				User user=this.userRepository.getById(id);
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setBirthAt(birthAt);
				user.setUrlImage(urlImage);
				user.setEmail(email);
				user.setPassword(password);
				user.setStatus(status);
				user=this.userRepository.save(user);
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

	private User addUserRole(User user, String name) {
		try {
			List<Role> roles;
			Role role=roleService.findByName(name);
			if(user==null){ return null; }
			if(role==null){	return user; }
			roles=user.getRoles();
			roles.add(role);
			user.setRoles(roles);
			return user;
		}catch (NullPointerException e) {
			System.out.println("Erreur relation user Role null");
			return user;
		}
	}

	public List<Lesson> findLessonsByUserId(Long userId){
		List<Lesson> lessons=new LinkedList<Lesson>();
		Lesson lesson;
		List<UserLesson> userLessons=this.userLessonRepository.findByUserLessonPK_user_Id(userId);
		for(UserLesson userLesson:userLessons){
			UserLessonPK userLessonPK=userLesson.getUserLessonPK();
			lesson=userLessonPK.getLesson();
			lessons.add(lesson);
		}
		return lessons;
	}



	public Map<String, Float> findNoteAndLessonsForUser(Long userId){
		Map<String, Float> bulletin=new HashMap<String,Float>();
		User user=this.getById(userId);
		Role role=new Role("Student");
		if(!user.getRoles().contains(role)){
			return null;
		}
		Lesson lesson;
		List<UserLesson> userLessons=userLessonRepository.findByUserLessonPK_user_Id(userId);
		for(UserLesson userLesson:userLessons){
			lesson=userLesson.getUserLessonPK().getLesson();
			bulletin.put(lesson.getName(),userLesson.getResultat());
		}
		return bulletin;

	}

}
