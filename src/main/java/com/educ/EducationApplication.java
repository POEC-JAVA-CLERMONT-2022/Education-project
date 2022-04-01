package com.educ;


import java.util.List;

import com.educ.entity.*;

import com.educ.entity.Role;
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

	public static void main(String[] args) {
		SpringApplication.run(EducationApplication.class, args);
	}
	
	@EventListener(classes= {ApplicationStartedEvent.class})
	public void applicationStarted() {

	}

}
