package com.example.educ.models;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;



public class User {
	
	private String firstName;
	private String lastName;
	private LocalDate birthAt;
	private Byte[] image;
	private String email;
	private String password;
	private String status;
	private Review review;
	
	private List<UserRole> userRoles;
	
	private List<Lesson> lessons;
	
	
	
	
	public User(String firstName, String lastName, String email, String status, List<UserRole> userRoles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.status = status;
		this.userRoles = userRoles;
		this.lessons=new LinkedList<Lesson>();
		this.review=null;
	}

	public User(String email) {
		super();
		this.email = email;
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



	public Byte[] getImage() {
		return image;
	}



	public String getEmail() {
		return email;
	}



	public String getStatus() {
		return status;
	}



	public Review getReview() {
		return review;
	}



	public List<UserRole> getUserRoles() {
		return userRoles;
	}



	public List<Lesson> getLessons() {
		return lessons;
	}


	

	

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", birthAt=" + birthAt + ", email=" + email
				+ ", status=" + status + ", review=" + review + ", userRoles=" + userRoles + ", lessons=" + lessons
				+ "]";
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
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

