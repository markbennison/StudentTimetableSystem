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
public class RoomDetails implements Serializable {
	private Integer id;
	private String name; 
	private String description;
	
	/**
	 * Constructor
	 * @param id
	 * @param name
	 * @param description
	 */
	public RoomDetails(Integer id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
		/**
	 * Constructor
	 * @param name
	 * @param description
	 */
	public RoomDetails(String name, String description) {
		this.name = name;
		this.description = description;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
