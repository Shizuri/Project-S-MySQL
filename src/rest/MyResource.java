package rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import models.Chat;

@Path("chat")
public class MyResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Chat> getAllMessages() {
		List<Chat> resultList = new ArrayList<>();

		try { // this is needed if the JDBC driver (mysql-connector-java-8.0.13.jar) is in the
				// WebContent/WEB-INF/lib folder and not in the Tomcat/lib folder.
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // this is needed if the JDBC driver (mysql-connector-java-8.0.13.jar) is in the
			// WebContent/WEB-INF/lib folder and not in the Tomcat/lib folder.

		Connection myConn = null;
		PreparedStatement myPrepStmt = null; // <-- PreparedStatement, NOT Statement
		ResultSet myRs = null;

		String dbUrl = "jdbc:mysql://localhost:3306/project_s";
		String user = "user1";
		String password = "test2";

		try { // write message to database
			myConn = DriverManager.getConnection(dbUrl, user, password);
			String sql = "SELECT * FROM messages;";
			myPrepStmt = myConn.prepareStatement(sql);
			// above this is boilerplate text that is repeated every time I work with JDBC.
			myRs = myPrepStmt.executeQuery();

			while (myRs.next()) {
				String message = myRs.getString("message");
				String time = myRs.getString("time");
				String name = myRs.getString("name");
				Chat chat = new Chat(message, time, name);
				resultList.add(chat);
			}

			// below this is boilerplate text that is repeated every time I work with JDBC.
		} catch (Exception e) {
			System.out.println("PROBLEM");
			System.out.println(e.getLocalizedMessage());
		} finally {
			if (myRs != null) {
				try {
					myRs.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (myPrepStmt != null) {
				try {
					myPrepStmt.close();
				} catch (SQLException e) {
					/* ignored */}
			}
			if (myConn != null) {
				try {
					myConn.close();
				} catch (SQLException e) {
					/* ignored */}
			}
		}

		return resultList;
	}

//	@GET
//	@Produces(MediaType.TEXT_PLAIN)
//	public String testMethod() {
//		return "It works!";
//	}
}
