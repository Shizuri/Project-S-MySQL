package models;

public class User {

	private String userName, password, hint;

	public User(String userName, String password, String hint) {
		super();
		this.userName = userName;
		this.password = password;
		this.hint = hint;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}
}
