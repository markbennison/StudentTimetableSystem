/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 *
 * @author markb
 */
public class ClassListDetails implements Serializable {
	private ClassDetails classid;
	private UserDetails userid;
	private String role;
	
	public ClassListDetails(ClassDetails classid, UserDetails userid) {
		this.classid = classid; 
		this.userid = userid;
		role = "student";
	}
	
	public ClassListDetails(ClassDetails classid, UserDetails userid, String role) {
		this.classid = classid; 
		this.userid = userid;
		this.role = role;
	}

	/**
	 * @return the classid
	 */
	public ClassDetails getClassid() {
		return classid;
	}

	/**
	 * @param classid the classid to set
	 */
	public void setClassid(ClassDetails classid) {
		this.classid = classid;
	}

	/**
	 * @return the userid
	 */
	public UserDetails getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(UserDetails userid) {
		this.userid = userid;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
		
}
