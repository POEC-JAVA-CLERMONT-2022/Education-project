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
	public static void main(String[] args) {
		SpringApplication.run(EducationApplication.class, args);
	}
	
	@EventListener(classes= {ApplicationStartedEvent.class})
	public void applicationStarted() {


	}

}
