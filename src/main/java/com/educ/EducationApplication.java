package com.educ;


import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import com.educ.data.LessonRepository;
import com.educ.data.RoleRepository;
import com.educ.entity.*;

import com.educ.entity.Role;
import com.educ.services.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import com.educ.services.LessonService;
import com.educ.services.ModuleeService;
import com.educ.services.ReviewService;
import com.educ.services.RoleService;
import com.educ.services.UserService;

@SpringBootApplication
public class EducationApplication {

	@Autowired
	private UserService userService;
	
	@Autowired
	private LessonService lessonService;

	@Autowired
	private ModuleeService moduleService;
	 
	@Autowired
	private ReviewService reviewService;
	 
	 @Autowired
	private RoleService roleService;

	 @Autowired
	 private RoleRepository roleRepository;

	 @Autowired
	 private LessonRepository lessonRepository;

	public static void main(String[] args) {
		SpringApplication.run(EducationApplication.class, args);
	}
	
	@EventListener(classes= {ApplicationStartedEvent.class})
	public void applicationStarted() {

		/* Load roles by default */
		Role roleM=this.roleService.createRole("Member");
		Role roleA=this.roleService.createRole("Admin");
		Role roleT=this.roleService.createRole("Teacher");
		Role roleS=this.roleService.createRole("Student");

		/* Load user ADMIN */

		String firstName="Admin";
		String lastName="Admin";
		LocalDate birthAt=LocalDate.of(2022,04,20);
		String urlImage="https://thumbs.dreamstime.com/z/test.jpg";
		String email="admin@gmail.com";
		String password="xxxx";
		String status="Admin application";
		LinkedList<Role> roles ;
		UserDTO userDTO=new UserDTO(firstName,lastName,birthAt,urlImage,email,password,status);
		User userAdmin = this.userService.createUser(userDTO);
		//User userAdmin = this.userService.createUser(userDTO);
		//userService.add(userDTO.getEmail(), "Admin");

		/*
		List<Lesson> lessons=lessonService.findAll();
		System.out.println(lessons.size()+" Lessons trouvées");

		List<Lesson> lessons = lessonService.findAllLessonsModules();
		System.out.println(lessons);



		System.out.println("Application démarée");

		List<User> users=userService.findAll();
		System.out.println(users.size()+" users trouvés");










		List<Lesson> lessons=lessonService.findAll();
		System.out.println(lessons.size()+" Lessons trouvées");
		
		//User user1=this.userService.create("Wendy", "kjkjlkj", "sWendygrouche@yahoo.com", "Admin systeme ");
		//System.out.println("MAIL ==========>    "+user1.getEmail());
		Role role=this.roleService.createRole("Teacher");

		Role role1=this.roleService.createRole("Admin");
		//Role role0=this.roleService.createRole("Admin");
    	this.roleService.updateRole(9L, "Admin");
		this.roleService.updateRole(9L, "Student");
		System.out.println(this.roleService.findByName("Admin"));


    	Lesson lesson1 = this.lessonService.createLesson("LessonFRançais", "descriptiondelale", 20.50f, Language.EN, Level.ADVANCE);
		Modulee module1 = this.moduleService.createModule("Modulo1");
		this.moduleService.updateModule(1L, "Modulo Principiante");
*/
		//this.lessonService.deleteLesson(1L);

		/*if (users.size()==0) {
			User user1=this.userService.create("Wendy", "kjkjlkj", "sWendygrouche@yahoo.com", "Admin systeme ");
			user1.getEmail();
		}*/

		//this.lessonService.updateLesson(2L, "Curso de Espanol", "descriptiondelale", 20.50f, Language.EN, Level.ADVANCE);

	}

}
