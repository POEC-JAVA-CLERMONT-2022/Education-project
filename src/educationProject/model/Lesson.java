package educationProject.model;

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
		this.price=0.0f;
		this.teachers=new LinkedList<User>();
		this.students=new LinkedList<User>();
		this.admins=new LinkedList<User>();
		this.modules=new LinkedList<Module>();
		this.levels=new LinkedList<Level>();
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
