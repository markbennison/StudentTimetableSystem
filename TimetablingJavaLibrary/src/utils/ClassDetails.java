/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author markb
 */
public class ClassDetails implements Serializable {
	private Integer id;
	private Date startdate;
	private Date enddate;
	private ModuleDetails moduleid;

	public ClassDetails(Integer id, ModuleDetails moduleid, Date startdate, Date enddate) {
		this.id = id; 
		this.moduleid = moduleid;
		this.startdate = startdate;
		this.enddate = enddate;
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
	 * @return the startdate
	 */
	public Date getStartdate() {
		return startdate;
	}

	/**
	 * @param startdate the startdate to set
	 */
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	/**
	 * @return the enddate
	 */
	public Date getEnddate() {
		return enddate;
	}

	/**
	 * @param enddate the enddate to set
	 */
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	/**
	 * @return the moduleid
	 */
	public ModuleDetails getModuleid() {
		return moduleid;
	}

	/**
	 * @param moduleid the moduleid to set
	 */
	public void setModuleid(ModuleDetails moduleid) {
		this.moduleid = moduleid;
	}
	
}
