/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;

/**
 *
 * @author markb
 */
public class UserDetails implements Serializable {
	private Integer id;
	private String username;
	private String password;
	private String forename;
	private String surname;
	private RoleDetails roleid;

	public UserDetails(Integer id, String username, String password, String forename, String surname, RoleDetails roleid) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.forename = forename;
		this.surname = surname;
		this.roleid = roleid;
	}	

	// Constructor to ignore password
	public UserDetails(Integer id, String username, String forename, String surname, RoleDetails roleid) {
		this.id = id;
		this.username = username;
		this.forename = forename;
		this.surname = surname;
		this.roleid = roleid;
	}	
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the forename
	 */
	public String getForename() {
		return forename;
	}

	/**
	 * @param forename the forename to set
	 */
	public void setForename(String forename) {
		this.forename = forename;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the roleid
	 */
	public RoleDetails getRoleid() {
		return roleid;
	}

	/**
	 * @param roleid the roleid to set
	 */
	public void setRoleid(RoleDetails roleid) {
		this.roleid = roleid;
	}
	
}
