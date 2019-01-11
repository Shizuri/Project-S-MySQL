package controllers;

import database.UsersDataBaseSimulator;

public class TESTER {

	public static void main(String[] args) {
		String userName = "u";
		String password = "p";
		String hint = "h";
		
		for(String name : UsersDataBaseSimulator.USERS.keySet()) {
			System.out.println("Name: " + name + " | Value: " + UsersDataBaseSimulator.USERS.get(name));
		}
		String sql = "INSERT INTO users VALUES('"+ userName +"', '" + password + "', " + hint + "');";
		System.out.println(sql);
	}

}
