package controllers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import database.UsersDataBaseSimulator;

@ManagedBean(name="loginController")
@SessionScoped
public class LoginController implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name, password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String login() {
		if(UsersDataBaseSimulator.USERS.get(name) != null) {
			if(UsersDataBaseSimulator.USERS.get(name).getPassword().equals(password)) {
				return "main-page?faces-redirect=true";
			}
		}
		return "error";
	}
}
