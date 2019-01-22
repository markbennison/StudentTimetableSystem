/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import TimetablingSystem.ModuleFacadeRemote;
import TimetablingSystem.UserFacadeRemote;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import utils.ModuleDetails;
import utils.UserDetails;

/**
 *
 * @author markb
 */
@Named(value = "moduleManagedBean")
@SessionScoped
public class ModuleManagedBean implements Serializable {

	private Logger logger = Logger.getLogger("managedbeans.ModuleManagedBean"); 
	private static ModuleFacadeRemote remote;
	
	private ArrayList<ModuleDetails> list;
	
	private Integer id;
	private String title;
	private String modulecode;
	private UserDetails leader;
	private Integer leadervalue;
	
	private Integer deletedrecord;
	
	public ModuleManagedBean() {
		try {
			Context initial = new InitialContext();
			remote = (ModuleFacadeRemote) initial.lookup("modulefacade");
		} 
		catch (Exception ex) {
			System.err.println("Caught an exception:");
			ex.printStackTrace();
		} 
	}

	/**
	 * @return the list
	 */
	public ArrayList<ModuleDetails> getList() {
		return (ArrayList) remote.findAll();
	}

	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<ModuleDetails> list) {
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

	/**
	 * @return the leadervalue
	 */
	public Integer getLeadervalue() {
		if (leader == null){
			leadervalue = 0;
		}
		else{
			leadervalue = leader.getId();
		}
		return leadervalue;
	}

	/**
	 * @param leadervalue the leadervalue to set
	 */
	public void setLeadervalue(Integer leadervalue) {
		this.leadervalue = leadervalue;
		try {
			UserFacadeRemote remoteLeader;
			Context initialLeader = new InitialContext();
			remoteLeader = (UserFacadeRemote) initialLeader.lookup("userfacade");
			this.leader = remoteLeader.find(leadervalue);
		}
		catch (Exception e) {
			logger.warning("Leader could not be found");
			setMessage(e.getMessage() + "\n" + ", Leader could not be found");
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
		title = null;
		modulecode = null;
		leader = null;
		leadervalue = null;
	}
	
	public String createRecord() {
		try {
			remote.createModule(new ModuleDetails(getId(), title, modulecode, leader));
			setMessage("Record successfully added");
			resetFields();
		} 
		catch (Exception e) {
			logger.warning("Record could not be created");
			setMessage(e.getMessage() + "\n" + ", Record could not be created");
		}
		return "message";
	} 
 
    public String getRecord() {
		try {
			ModuleDetails temp = remote.find(getId());
			id = temp.getId();
			title = temp.getTitle();
			modulecode = temp.getModulecode();
			leader = temp.getLeader();
			return "viewmodule";
		}
		catch (Exception e) {
			logger.warning("Record could not be found");
			setMessage(e.getMessage() + "\n" + ", Record could not be found");
			return "message";
		}
	} 
	
	public String getRecord(Integer requestedId) {
		try {
			ModuleDetails temp = remote.find(requestedId);
			id = temp.getId();
			title = temp.getTitle();
			modulecode = temp.getModulecode();
			leader = temp.getLeader();
			return "viewmodule";
		}
		catch (Exception e) {
			logger.warning("Record could not be found");
			setMessage(e.getMessage() + "\n" + ", Record could not be found");
			return "message";
		}
	} 
 
    public String editRecord() {
		try {
			remote.editModule(new ModuleDetails(getId(), title, modulecode, leader));
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
			remote.removeModule(getDeletedrecord());
			setMessage("Record successfully deleted");
			resetFields();
		}
		catch(Exception e){
			setMessage(e.getMessage() + "\n" + ", Record could not be deleted");
		}
		return "message";
	} 
	
	public String deleteRecord(Integer requestedId){
		try{
			deletedrecord = requestedId;
			remote.removeModule(getDeletedrecord());
			setMessage("Record successfully deleted");
			resetFields();
		}
		catch(Exception e){
			setMessage(e.getMessage() + "\n" + ", Record could not be deleted");
		}
		return "message";
	} 
	
	public void validateTitle(FacesContext context, UIComponent component, Object value)
    {
		String message = "";
		String stringToCheck = (String)value;
		stringToCheck = stringToCheck.trim();

		if (!(stringToCheck.length() > 0 && stringToCheck.length() <= 50))
		{
			((UIInput)component).setValid(false); 
			message="Error: Module Title has a maximum length of 50 characters.";
			context.addMessage(component.getClientId(), new FacesMessage(message));
		}
	}
	
	public void validateModulecode(FacesContext context, UIComponent component, Object value)
    {
		String message = "";
		String stringToCheck = (String)value;
		stringToCheck = stringToCheck.trim();

		if (!(stringToCheck.length() > 0 && stringToCheck.length() <= 20))
		{
			((UIInput)component).setValid(false); 
			message="Error: Module Code has a maximum length of 20 characters.";
			context.addMessage(component.getClientId(), new FacesMessage(message));
		}
	}
}
