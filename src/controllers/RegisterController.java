package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class RegisterController {

	private String userName, password, hint;

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
	
	public String register() {
		
		try { //this is needed if the JDBC driver (mysql-connector-java-8.0.13.jar) is in the WebContent/WEB-INF/lib folder and not in the Tomcat/lib folder. 
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} //this is needed if the JDBC driver (mysql-connector-java-8.0.13.jar) is in the WebContent/WEB-INF/lib folder and not in the Tomcat/lib folder. 
		
		Connection myConn = null;
		PreparedStatement myPrepStmt = null; //<-- PreparedStatement, NOT Statement
		ResultSet myRs = null;

		String dbUrl = "jdbc:mysql://localhost:3306/project_s";
		String user = "user1";
		String password = "test2";
		
		try { //find out if user is already in database
			myConn = DriverManager.getConnection(dbUrl, user, password);
			myPrepStmt = myConn.prepareStatement("select * from users");
			myRs = myPrepStmt.executeQuery();
			
			while(myRs.next()) {
				String value = myRs.getString("user_name");
				if(value.equals(this.userName)) {
					return "duplicate-user-name";
				}
			}
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
		
		
		try { // write user to database
		  myConn = DriverManager.getConnection(dbUrl, user, password);
		  String sql = "INSERT INTO users VALUES(?, ?, ?);";
		  myPrepStmt = myConn.prepareStatement(sql);
		  //above this is boilerplate text that is repeated every time I work with JDBC.
		  myPrepStmt.setString(1, this.userName);
		  myPrepStmt.setString(2, this.password);
		  myPrepStmt.setString(3, this.hint);
		  myPrepStmt.executeUpdate();

		  System.out.println("Insert complete.");
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
		
		return "index";
	}
}
