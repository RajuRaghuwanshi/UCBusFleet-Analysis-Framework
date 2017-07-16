package org.iiitb.bmtc.modal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JString {
	
	String response;

	public JString() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JString(String response) {
		super();
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	
	
}
