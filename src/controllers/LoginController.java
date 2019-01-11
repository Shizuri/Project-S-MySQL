package controllers;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
		
		Connection myConn = null;
		PreparedStatement myPrepStmt = null; //<-- PreparedStatement, NOT Statement
		ResultSet myRs = null;

		String dbUrl = "jdbc:mysql://localhost:3306/project_s";
		String user = "user1";
		String password = "test2";
		
		try { // write user to database
			  myConn = DriverManager.getConnection(dbUrl, user, password);
			  String sql = "SELECT * FROM users;";
			  myPrepStmt = myConn.prepareStatement(sql);
			  //above this is boilerplate text that is repeated every time I work with JDBC.
			  myRs = myPrepStmt.executeQuery();

			  while(myRs.next()) {
				  if(myRs.getString("user_name").equals(this.name) && myRs.getString("password").equals(this.password)) {
					  return "main-page?faces-redirect=true";
				  }
			  }
			  
			  //below this is boilerplate text that is repeated every time I work with JDBC.
			} catch (Exception e) {
			  System.out.println("PROBLEM");
			  System.out.println(e.getLocalizedMessage());
			} finally {
			    if (myRs != null) {
			        try {
			          myRs.close();
			        } catch (SQLException e) { /* ignored */}
			    }
			    if (myPrepStmt != null) {
			        try {
			          myPrepStmt.close();
			        } catch (SQLException e) { /* ignored */}
			    }
			    if (myConn != null) {
			        try {
			          myConn.close();
			        } catch (SQLException e) { /* ignored */}
			    }
			}
		
//		if(UsersDataBaseSimulator.USERS.get(name) != null) {
//			if(UsersDataBaseSimulator.USERS.get(name).getPassword().equals(password)) {
//				return "main-page?faces-redirect=true";
//			}
//		}
		return "error";
	}
}
