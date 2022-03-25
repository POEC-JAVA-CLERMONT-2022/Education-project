package com.educ.data;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.educ.models.User;

public class InMemoryDataProviderUser implements DataProviderUser {
	private ArrayList<User> users=new ArrayList<User>();
	private Map<User,Integer> map;
	
	public InMemoryDataProviderUser() {
		map=IntStream.range(0, users.size()).boxed().collect(Collectors.toMap(users::get, Function.identity()));
	}

	@Override
	public ArrayList<User> getAll() {
		return users;
	}

	@Override
	public void addUser(User user) {
		users.add(user);
		
	}
	
	@Override
	public int getIndexUser(User user) {
		
		return map.get(user);
		/*int index=0;
		for(User u:users) {
			if(u.equals(user)) {
				return index;
			}
			index++;
		}
		return -1;*/
	}
	

	@Override
	public void modifUser(User userO,User userN) {
		
		users.set(this.getIndexUser(userO),userN);
		users.stream();
		
	}

	@Override
	public void deleteUser(User user) {
		users.remove(this.getIndexUser(user));
		
	}

	
	

}
