package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="fpc")
public class ForgotPasswordController {

	private String name, hint, password;
	
	public String reset() {
		
		Connection myConn = null;
		PreparedStatement myPrepStmt = null; //<-- PreparedStatement, NOT Statement
		ResultSet myRs = null;

		String dbUrl = "jdbc:mysql://localhost:3306/project_s";
		String dbUser = "user1";
		String dbPassword = "test2";
		
		try { // write user to database
			  myConn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			  String sql = "SELECT * FROM users;";
			  myPrepStmt = myConn.prepareStatement(sql);
			  //above this is boilerplate text that is repeated every time I work with JDBC.
			  //myPrepStmt.setInt(1, id); this is here just as an example.
			  myRs = myPrepStmt.executeQuery();

			  while(myRs.next()) {
				  String userName = myRs.getString("user_name");
				  String hint = myRs.getString("hint");
				  //String password = myRs.getString("password");
				  
				  if(userName.equals(this.name) && hint.equals(this.hint)) {
					  String sqlPasswordChange = "UPDATE users SET password=? WHERE user_name=?;";
					  PreparedStatement passChangePrepStmt = myConn.prepareStatement(sqlPasswordChange);
					  passChangePrepStmt.setString(1, this.password);
					  passChangePrepStmt.setString(2, userName);
					  passChangePrepStmt.executeUpdate();
					  passChangePrepStmt.close();
					  return "password-successfully-changed";
				  }
			  }
			  
			  //below this is boilerplate text that is repeated every time I work with JDBC.
			} catch (Exception e) {
			  System.out.println("PROBLEM");
			  System.out.println("Localized message: " + e.getLocalizedMessage());
			  System.out.println("Message: " + e.getMessage());
			  System.out.println("Cause: " + e.getCause());
			  System.out.println("Stack trace: ");
			  e.printStackTrace();
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
//			if(UsersDataBaseSimulator.USERS.get(name).getHint().equals(hint)) {
//				UsersDataBaseSimulator.USERS.get(name).setPassword(password);
//				return "index";
//			}
//		}
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
