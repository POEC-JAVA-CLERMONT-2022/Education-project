package com.educ.entity;



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
	
	/*@ManyToMany(mappedBy = "lessons")
	private List<User> users; */
	
	/*private List<User> students;
	private List<User> admins;*/
	
	//@OneToMany(mappedBy = "lesson", cascade=CascadeType.REMOVE, orphanRemoval = true)
	@OneToMany(mappedBy = "lesson")
	private List<Modulee> modules;
	
	@Column(name="level")
	private Level level;
	
	@Column(name="language")
	private Language language;
	
	public Lesson() {
		
	}



	public Lesson(String name, String description, float price, Language language, Level level) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.language = language;
		this.level = level;
 		this.modules=new LinkedList<Modulee>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	/*public void setModules(List<Modulee> modules) {
		this.modules = modules;
	}*/


	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public float getPrice() {
		return price;
	}

	public Level getLevel() {
		return level;
	}

	public Language getLanguage() {
		return language;
	}

	public List<Modulee> getModules() {
		return modules;
	}



	public void setLevel(Level level) {
		this.level = level;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lesson other = (Lesson) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Lesson {id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", modules=" + modules + ", level=" + level + ", language=" + language + "}";
	}
}
