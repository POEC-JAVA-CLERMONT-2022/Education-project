package educationProject.service;


import java.util.LinkedList;
import java.util.List;
import educationProject.model.User;
import educationProject.model.UserRole;

public class UserService {
	private List<UserRole> userRoles;
	private List<User> users;



	public UserService() {
		super();
		this.users = new LinkedList<User>();
	}

	public User searchUser(String email) {

		for(User u: users) {
			if(u.getEmail().equals(email)) {
				return u;
			}else {

			}
		}
		return null;
	}

	public void createUser(String firstName, String lastName, String email, String status, List<UserRole> userRoles) {
		if(this.searchUser(email)==null) {
			User user=new User(firstName, lastName, email, status, userRoles);
			this.users.add(user);
		}else {
			//user existe deja
		}
	}

	private void addUserRole(String email, UserRole userRole) {
		User user=this.searchUser(email);
		if(user!=null) {
			this.userRoles=user.getUserRoles();
			this.userRoles.add(userRole);
			user.setUserRoles(userRoles);
			this.modifUser(user);
		}
	}

	public void modifUser(User user) {
		User u=this.searchUser(user.getEmail());
		if(u!=null) {
			this.users.remove(u);
			this.users.add(user);
		}
	}

	public void deleteUser(User user) {

		User u=this.searchUser(user.getEmail());
		if(u!=null) {
			this.users.remove(u);
		}
	}



}
