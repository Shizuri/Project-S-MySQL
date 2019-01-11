package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ListUsersController {

	public List<String> getUsersList (){
		
		List<String> theList = new ArrayList<String>();
		
		Connection myConn = null;
		PreparedStatement myPrepStmt = null; //<-- PreparedStatement, NOT Statement
		ResultSet myRs = null;

		String dbUrl = "jdbc:mysql://localhost:3306/project_s";
		String user = "user1";
		String password = "test2";
		
		try { // write user to database
			  myConn = DriverManager.getConnection(dbUrl, user, password);
			  String sql = "SELECT user_name FROM users;";
			  myPrepStmt = myConn.prepareStatement(sql);
			  //above this is boilerplate text that is repeated every time I work with JDBC.
			  //myPrepStmt.setInt(1, id); this is here just as an example.
			  myRs = myPrepStmt.executeQuery();

			  while(myRs.next()) {
				  String userName = myRs.getString("user_name");
				  theList.add(userName);
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
		
		return theList;
	}
}
