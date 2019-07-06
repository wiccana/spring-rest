package com.restful.app.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	
	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Nora", new Date()));
		users.add(new User(3, "Leon ", new Date()));
	}
	
	private static int usersCount = 3;
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		if(user.getId()==null) {
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	public User findUser(int id) {
		for(User user: users) {  //TODO: mejorar iterador
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteUser(int id) {
		for(User user: users) { //TODO: mejorar iterador
			if(user.getId()==id) {
				users.remove(id);
				return user;
			}
		}
		return null;
	}

}
