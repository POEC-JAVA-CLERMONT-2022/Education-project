package com.educ;

import com.educ.data.RoleRepository;
import com.educ.entity.*;
import com.educ.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
public class EducationApplication {

	@Autowired
	private RoleService roleService;

	@Autowired
	private ModuleeService moduleeService;

	@Autowired
	private VideoService videoService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private ReviewService reviewService;
	@Autowired
	private LessonService lessonService;

	public static void main(String[] args) {
		SpringApplication.run(EducationApplication.class, args);
	}
	
	@EventListener(classes= {ApplicationStartedEvent.class})
	public void applicationStarted() {

		/* Load roles by default */
		Role roleM= roleService.createRole("Member");
		Role roleA=this.roleService.createRole("Admin");
		Role roleT=this.roleService.createRole("Teacher");
		Role roleS=this.roleService.createRole("Student");

		Video video1 = videoService.createVideo("Video1","www.videos.com/video1",LocalTime.now());
		Video video2 = videoService.createVideo("Video2","www.videos.com/video2", LocalTime.now());

		User user1 = userService.createUser("Wendy","Pariente", LocalDate.now(),"https://s3.com/photo/wp.png","w@gmail.com","password","Technicienne");




	}
}
