package com.educ.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
	@Id
	@Column(name="id", nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 
	@Column(name="roleName", length=150)
	private String roleName;

	public Role(String roleName) {
		super();
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}
	
	
	
	
	
	
	

}
