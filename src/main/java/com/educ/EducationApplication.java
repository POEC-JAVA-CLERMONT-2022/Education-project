package com.educ;

import java.time.Clock;
import java.util.List;

import com.educ.data.RoleRepository;
import com.educ.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import com.educ.entity.Lesson;
import com.educ.entity.User;
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

	public static void main(String[] args) {
		SpringApplication.run(EducationApplication.class, args);
	}
	
	@EventListener(classes= {ApplicationStartedEvent.class})
	public void applicationStarted() {
		System.out.println("Application démarée");
		List<User> users=userService.findAll();
		System.out.println(users.size()+" users trouvés");
		List<Lesson> lessons=lessonService.findAll();
		System.out.println(lessons.size()+" Lessons trouvées");
		
		User user1=this.userService.create("Wendy", "kjkjlkj", "sWendygrouche@yahoo.com", "Admin systeme ");
		System.out.println("MAIL ==========>    "+user1.getEmail());

		Role role=this.roleService.createRole("Teacher");

		Role role0=this.roleService.createRole("Admin");

		System.out.println(this.roleService.findByName("Admin"));

		Role role1=this.roleService.createRole("Admin");

		this.roleService.updateRole(5L, "Admin");
		this.roleService.updateRole(5L, "Student");

		//this.roleService.deleteRole(1L);

		/*if (users.size()==0) {
			User user1=this.userService.create("Wendy", "kjkjlkj", "sWendygrouche@yahoo.com", "Admin systeme ");
			user1.getEmail();
		}*/
	}

}
