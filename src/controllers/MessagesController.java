package controllers;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import database.MessagesDataBaseSimulator;
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
			Chat chat = new Chat(message.trim(), LocalTime.now().format(dtf), loginConroller.getName());
			MessagesDataBaseSimulator.MESSAGES.add(chat);
			this.message = "";
		}
		return "main-page?faces-redirect=true";
	}

	public List<Chat> getMessagesList() { // will not work if it does not start with get !!!
		return MessagesDataBaseSimulator.MESSAGES;
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

}
