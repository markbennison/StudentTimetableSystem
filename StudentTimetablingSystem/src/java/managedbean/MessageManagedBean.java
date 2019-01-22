/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author markb
 */
@Named(value = "messageManagedBean")
@SessionScoped
public class MessageManagedBean implements Serializable {

	private String message;
	/**
	 * Creates a new instance of MessageManagedBean
	 */
	public MessageManagedBean() {
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
