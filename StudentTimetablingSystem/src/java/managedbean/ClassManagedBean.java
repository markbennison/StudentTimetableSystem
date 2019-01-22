/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import TimetablingSystem.ClassFacadeRemote;
import TimetablingSystem.ModuleFacadeRemote;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import utils.ClassDetails;
import utils.ModuleDetails;

/**
 *
 * @author markb
 */
@Named(value = "classManagedBean")
@SessionScoped
public class ClassManagedBean implements Serializable {
	
	private Logger logger = Logger.getLogger("managedbeans.ClassManagedBean"); 
	private static ClassFacadeRemote remote;
	
	private ArrayList<ClassDetails> list;
	
	private Integer id;
	private Date startdate;
	private Date enddate;
	private ModuleDetails moduleid;
	private Integer moduleidvalue;

	private Integer deletedrecord;
	
	public ClassManagedBean() {
		try {
			Context initial = new InitialContext();
			remote = (ClassFacadeRemote) initial.lookup("classfacade");
			
		} 
		catch (Exception ex) {
			System.err.println("Caught an exception:");
			ex.printStackTrace();
		} 
	}

	/**
	 * @return the list
	 */
	public ArrayList<ClassDetails> getList() {
		return (ArrayList) remote.findAll();
	}

	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<ClassDetails> list) {
		this.list = list;
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

	/**
	 * @return the moduleidvalue
	 */
	public Integer getModuleidvalue() {
		if (moduleid == null){
			moduleidvalue = 0;
		}
		else{
			moduleidvalue = moduleid.getId();
		}
		return moduleidvalue;
	}

	/**
	 * @param moduleidvalue the moduleidvalue to set
	 */
	public void setModuleidvalue(Integer moduleidvalue) {
		this.moduleidvalue = moduleidvalue;
		try {
			ModuleFacadeRemote remoteModule;
			Context initialModule = new InitialContext();
			remoteModule = (ModuleFacadeRemote) initialModule.lookup("modulefacade");
			this.moduleid= remoteModule.find(moduleidvalue);
		}
		catch (Exception e) {
			logger.warning("Module could not be found");
			setMessage(e.getMessage() + "\n" + ", Module could not be found");
		}
	}

	public String getMessage() {
		FacesContext context = FacesContext.getCurrentInstance();
		MessageManagedBean messageBean = (MessageManagedBean)context.getApplication().createValueBinding("#{messageManagedBean}").getValue(context);
		return messageBean.getMessage();
	}

	public void setMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		MessageManagedBean messageBean = (MessageManagedBean)context.getApplication().createValueBinding("#{messageManagedBean}").getValue(context);
		messageBean.setMessage(message);
	}

	/**
	 * @return the deletedrecord
	 */
	public Integer getDeletedrecord() {
		return deletedrecord;
	}

	/**
	 * @param deletedrecord the deletedrecord to set
	 */
	public void setDeletedrecord(Integer deletedrecord) {
		this.deletedrecord = deletedrecord;
	}
	
	public void resetFields(){
		setDeletedrecord(null);
		setId(null);
		moduleid = null;
		startdate = null;
		enddate = null;
	}
	
	public String createRecord() {
		try {
			remote.createClass(new ClassDetails(getId(), moduleid, startdate, enddate));
			setMessage("Record successfully added");
		} 
		catch (Exception e) {
			logger.warning("Record could not be created");
			setMessage(e.getMessage() + "\n" + ", Record could not be created");
		}
		return "message";
	} 
 
    public String getRecord() {
		try {
			ClassDetails temp = remote.find(getId());
			setId(temp.getId());
			moduleid = temp.getModuleid();
			startdate = temp.getStartdate();
			enddate = temp.getEnddate();
			return "viewclass";
		}
		catch (Exception e) {
			logger.warning("Record could not be found");
			setMessage(e.getMessage() + "\n" + ", Record could not be found");
			return "message";
		}
	} 
	
	public String getRecord(Integer requestedId) {
		try {
			ClassDetails temp = remote.find(requestedId);
			setId(temp.getId());
			moduleid = temp.getModuleid();
			startdate = temp.getStartdate();
			enddate = temp.getEnddate();
			return "viewclass";
		}
		catch (Exception e) {
			logger.warning("Record could not be found");
			setMessage(e.getMessage() + "\n" + ", Record could not be found");
			return "message";
		}
	} 
 
    public String editRecord() {
		try {
			remote.editClass(new ClassDetails(getId(), moduleid, startdate, enddate));
			setMessage("Record successfully edited");
			resetFields();
		} 
		catch (Exception e) {
			setMessage(e.getMessage() + "\n" + ", Record could not be edited");
		}
		return "message";
	}
	
	public String deleteRecord(){
		try{
			remote.removeClass(getDeletedrecord());
			setMessage("Record successfully deleted");
		}
		catch(Exception e){
			setMessage(e.getMessage() + "\n" + ", Record could not be deleted");
		}
		return "message";
	} 
	
	public String deleteRecord(Integer requestedId){
		try{
			deletedrecord = requestedId;
			remote.removeClass(getDeletedrecord());
			setMessage("Record successfully deleted");
		}
		catch(Exception e){
			setMessage(e.getMessage() + "\n" + ", Record could not be deleted");
		}
		return "message";
	} 
	
}
