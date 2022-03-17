package educationProject.model;

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
	
	public User(String firstName, String lastName, String email, String status) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.status = status;
		this.lessons=new LinkedList<Lesson>();
		this.review=null;
		this.userRoles=new LinkedList<UserRole>();
	}
	
	@Override
	public String toString() {
		return "User {firstName=" + firstName + ", lastName=" + lastName + ", birthAt=" + birthAt + ", image="
				+ Arrays.toString(image) + ", email=" + email + ", password=" + password + ", status=" + status + "}";
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
