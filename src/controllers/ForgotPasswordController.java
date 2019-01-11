package controllers;

import javax.faces.bean.ManagedBean;

import database.UsersDataBaseSimulator;

@ManagedBean(name="fpc")
public class ForgotPasswordController {

	private String name, hint, password;
	
	public String reset() {
		if(UsersDataBaseSimulator.USERS.get(name) != null) {
			if(UsersDataBaseSimulator.USERS.get(name).getHint().equals(hint)) {
				UsersDataBaseSimulator.USERS.get(name).setPassword(password);
				return "index";
			}
		}
		return "reset-error";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}
}
