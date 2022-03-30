package com.educ.entity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@Entity
@Table(name="users")

public class User {
	@Id
	@Column(name="id", nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="firstName", length=150)
	private String firstName;
	
	@Column(name="lastName", length=150)
	private String lastName;
	
	@Column(name="birthAt", length=150)
	private LocalDate birthAt;
	
	@Column(name="urlImage")
	private String urlImage;
	
	@Column(name="email", length=150)
	private String email;
	
	@Column(name="password", length=150)
	private String password;
	
	@Column(name="status", length=150)
	private String status;
	
	/*@OneToMany(mappedBy ="user", fetch = FetchType.LAZY)*/
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade=CascadeType.REMOVE, orphanRemoval = true)
	private List<Review> reviews;
	

	@JoinColumn(name="role_id")
	@ManyToMany
	private List<Role> roles;
	
	public User() {
		
	}
		
	/*public User(String email) {
		super();
		this.email = email;
	}*/
		
	public User(String firstName, String lastName, String email, String status) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.status = status;
		//this.lessons=null;
		}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public LocalDate getBirthAt() {
		return birthAt;
	}

	public String getEmail() {
		return email;
	}



	public String getStatus() {
		return status;
	}
	
	public String getUrlImage() {
		return urlImage;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public String toString() {
		return "User {id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthAt=" + birthAt
				+ ", urlImage=" + urlImage + ", email=" + email + ", password=" + password + ", status=" + status
				+ ", review=" + reviews + ", roles=" + roles + "}";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email);
	}

}

