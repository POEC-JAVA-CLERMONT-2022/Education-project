package com.educ.data;

import java.util.ArrayList;

import com.educ.entity.User;

public interface DataProviderUser {
	public ArrayList<User> getAll();
	public void addUser(User user);
	public int getIndexUser(User user);
	public void modifUser(User userO,User userN);
	public void deleteUser(User user);
	
	

}
