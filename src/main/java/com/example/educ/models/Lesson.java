package com.example.educ.models;



import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Lesson {

	private String name;
	private String description;
	private float price;
	
	private List<User> teachers;
	private List<User> students;
	private List<User> admins;
	private List<Module> modules;
	
	private List<Level> levels;
	private Language language;
	
	public Lesson(String name) {
		super();
		this.name = name;
		this.description="";
		this.price=0.0f;
		this.teachers=new LinkedList<User>();
		this.students=new LinkedList<User>();
		this.admins=new LinkedList<User>();
		this.modules=new LinkedList<Module>();
		this.levels=new LinkedList<Level>();
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



	public List<User> getTeachers() {
		return teachers;
	}



	public List<User> getStudents() {
		return students;
	}



	public List<User> getAdmins() {
		return admins;
	}



	public List<Module> getModules() {
		return modules;
	}



	public List<Level> getLevels() {
		return levels;
	}



	public Language getLanguage() {
		return language;
	}



	@Override
	public String toString() {
		return "Lesson {name=" + name + ", description=" + description + ", price=" + price + "}";
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
	
	
}
