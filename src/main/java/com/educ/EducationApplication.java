package com.educ;

import com.educ.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import com.educ.services.RoleService;

@SpringBootApplication
public class EducationApplication {

	@Autowired
	private RoleService roleService;

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

	}
}
