package com.harlansoft.restfulwebservices.dao.users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.harlansoft.restfulwebservices.model.users.User;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	private static int userCount;
	static {
		users.add(new User(1, "Adam", new Date()));
		userCount = 1;
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User findOne(Integer id) {
		return users.stream()
			.filter(user -> user.getId().equals(id))
			.findAny()
			.orElse(null);
	}
	
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++userCount);
		}
		
		users.add(user);
		
		return user;
	}
	
	public User deleteById(Integer id) {
		User user = findOne(id);
		return user != null ? users.remove(users.indexOf(user)) : null;
	}
}
