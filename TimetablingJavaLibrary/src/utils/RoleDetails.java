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
public class RoleDetails implements Serializable {
	private Integer id;
	private String name;
	private int accesslevel;

	public RoleDetails(Integer id, String name, int accesslevel) {
		this.id = id;
		this.name = name;
		this.accesslevel = accesslevel;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the accesslevel
	 */
	public int getAccesslevel() {
		return accesslevel;
	}

	/**
	 * @param accesslevel the accesslevel to set
	 */
	public void setAccesslevel(int accesslevel) {
		this.accesslevel = accesslevel;
	}
}
