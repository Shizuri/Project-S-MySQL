package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import models.Chat;

@ManagedBean
public class MessagesController {

	@ManagedProperty(value = "#{loginController}")

	private LoginController loginConroller;

	private String message;

	public LoginController getLoginConroller() {
		return loginConroller;
	}

	public void setLoginConroller(LoginController loginConroller) {
		this.loginConroller = loginConroller;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String readMessage() {
		if(!message.trim().isEmpty()) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			String timeStamp = LocalTime.now().format(dtf);
			
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
			
			try { // write message to database
				  myConn = DriverManager.getConnection(dbUrl, user, password);
				  String sql = "INSERT INTO messages VALUES(?, ?, ?, NULL);";
				  myPrepStmt = myConn.prepareStatement(sql);
				  //above this is boilerplate text that is repeated every time I work with JDBC.
				  myPrepStmt.setString(1, this.message);
				  myPrepStmt.setString(2, timeStamp);
				  myPrepStmt.setString(3, this.loginConroller.getName());
				  myPrepStmt.executeUpdate();

				  System.out.println("Message insert complete.");
				  //below this is boilerplate text that is repeated every time I work with JDBC.
				} catch (Exception e) {
				  System.out.println("PROBLEM");
				  System.out.println(e.getLocalizedMessage());
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
			
			//DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			//Chat chat = new Chat(message.trim(), LocalTime.now().format(dtf), loginConroller.getName());
			//MessagesDataBaseSimulator.MESSAGES.add(chat);
			this.message = "";
		}
		return "main-page?faces-redirect=true";
	}

	public List<Chat> getMessagesList() { // will not work if it does not start with get !!!
		List<Chat> resultList = new ArrayList<>();
		
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
		
		try { // write message to database
			  myConn = DriverManager.getConnection(dbUrl, user, password);
			  String sql = "SELECT * FROM messages;";
			  myPrepStmt = myConn.prepareStatement(sql);
			  //above this is boilerplate text that is repeated every time I work with JDBC.
			  myRs = myPrepStmt.executeQuery();

			  while(myRs.next()) {
				  String message = myRs.getString("message");
				  String time = myRs.getString("time");
				  String name = myRs.getString("name");
				  Chat chat = new Chat(message, time, name);
				  resultList.add(chat);
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
		
		return resultList;
	}

//	Original work with strings, trying with objects now ^
//	
//	public String readMessage() {
//		MessagesDataBaseSimulator.StringMESSAGES.add(message);
//		return "main-page";
//	}
//	
//	public List<String> getMessagesList(){ //will not work if it does not start with get !!!
//		//return test;
//		return MessagesDataBaseSimulator.StringMESSAGES;
//	}
	
	// non MySQL methods:
/*
	public String readMessage() {
		if(!message.trim().isEmpty()) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			Chat chat = new Chat(message.trim(), LocalTime.now().format(dtf), loginConroller.getName());
			MessagesDataBaseSimulator.MESSAGES.add(chat);
			this.message = "";
		}
		return "main-page?faces-redirect=true";
	}

	public List<Chat> getMessagesList() { // will not work if it does not start with get !!!
		return MessagesDataBaseSimulator.MESSAGES;
	}
 */

}
