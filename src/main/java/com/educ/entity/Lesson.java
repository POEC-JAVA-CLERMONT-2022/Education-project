package com.educ.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
//@JsonIgnoreProperties({"hibernateLazyInitializer"})
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
	@PositiveOrZero
	private float price;

	@Column(name="level")
	private Level level;

	@Column(name="language")
	private Language language;

	/* just like this it creates a new table lessons_modulees
	 * Nothing to declare in child Modulee */
	@OneToMany
	private List<Modulee> modulees;

	public Lesson() {

	}

	public Lesson(String name, String description, float price, Language language, Level level) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.language = language;
		this.level = level;

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

	public List<Modulee> getModulees() {
		return modulees;
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
		return "Lesson{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", price=" + price +
				//", modules=" + modules +
				", level=" + level +
				", language=" + language +
				'}';
	}
}
