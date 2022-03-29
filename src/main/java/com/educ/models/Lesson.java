package com.educ.models;



import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="lessons")
public class Lesson {
	@Id
	@Column(name="id", nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="price")
	private float price;
	
	@ManyToMany(mappedBy = "lessons")
	private List<User> users;
	
	/*private List<User> students;
	private List<User> admins;*/
	
	@OneToMany(mappedBy = "lesson", cascade=CascadeType.REMOVE, orphanRemoval = true)
	private List<Module> modules;
	
	@Column(name="level")
	private Level level;
	
	@Column(name="language")
	private Language language;
	
	public Lesson() {
		
	}
	
}
