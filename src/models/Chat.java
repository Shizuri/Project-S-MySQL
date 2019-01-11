package models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Chat {

	private String message;
	private String time;
	private String name;

	public Chat(String message, String time, String name) {
		this.message = message;
		this.time = time;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
