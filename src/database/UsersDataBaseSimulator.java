package database;

import java.util.HashMap;
import java.util.Map;

import models.User;

public class UsersDataBaseSimulator {

	public static final Map<String,User> USERS = new HashMap<String, User>();
	
	static {
		User u1 = new User("Zdravko", "123", "blah");
		USERS.put("Zdravko", u1);
	}
}
