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
public class ModuleDetails implements Serializable {
	private Integer id;
	private String title;
	private String modulecode;
	private UserDetails leader;

	public ModuleDetails(Integer id, String title, String modulecode, UserDetails leader) {
		this.id = id;
		this.title = title;
		this.modulecode = modulecode;
		this.leader = leader;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the modulecode
	 */
	public String getModulecode() {
		return modulecode;
	}

	/**
	 * @param modulecode the modulecode to set
	 */
	public void setModulecode(String modulecode) {
		this.modulecode = modulecode;
	}

	/**
	 * @return the leader
	 */
	public UserDetails getLeader() {
		return leader;
	}

	/**
	 * @param leader the leader to set
	 */
	public void setLeader(UserDetails leader) {
		this.leader = leader;
	}
}
