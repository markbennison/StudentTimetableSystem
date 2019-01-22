/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import TimetablingSystem.RoleFacadeRemote; 
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
import utils.RoleDetails;

/**
 *
 * @author markb
 */
@Named(value = "roleManagedBean")
@SessionScoped
public class RoleManagedBean implements Serializable {

	private Logger logger = Logger.getLogger("managedbeans.RoleManagedBean"); 
	private static RoleFacadeRemote remote;
	
	private ArrayList<RoleDetails> list;
	
	private Integer id;
	private String name;
	private int accesslevel;
	
	private String message;
	private Integer deletedrecord;
	
	/**
	 * Creates a new instance of RoleManagedBean
	 */
	public RoleManagedBean() {
		try {
			Context initial = new InitialContext();
			remote = (RoleFacadeRemote) initial.lookup("rolefacade");
		} 
		catch (Exception ex) {
			System.err.println("Caught an exception:");
			ex.printStackTrace();
		} 
	}

	/**
	 * @return the list
	 */
	public ArrayList<RoleDetails> getList() {
		return (ArrayList) remote.findAll();
	}

	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<RoleDetails> list) {
		this.list = list;
	}
	
	public ArrayList<String> getListNames() {
		ArrayList<String> names = new ArrayList();
		for(RoleDetails record : this.list) {
			names.add(record.getName());
		}
		
		return names;
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
	
	public String createRecord() {
		try {
			remote.createRole(new RoleDetails(getId(), name, accesslevel));
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
			RoleDetails temp = remote.find(getId());
			id = temp.getId();
			name = temp.getName();
			accesslevel = temp.getAccesslevel();
			return "viewrole";
		}
		catch (Exception e) {
			logger.warning("Record could not be found");
			setMessage(e.getMessage() + "\n" + ", Record could not be found");
			return "message";
		}
	} 
	
	public String getRecord(Integer requestedId) {
		try {
			RoleDetails temp = remote.find(requestedId);
			id = temp.getId();
			name = temp.getName();
			accesslevel = temp.getAccesslevel();
			return "viewrole";
		}
		catch (Exception e) {
			logger.warning("Record could not be found");
			setMessage(e.getMessage() + "\n" + ", Record could not be found");
			return "message";
		}
	} 
 
    public String editRecord() {
		try {
			remote.editRole(new RoleDetails(getId(), name, accesslevel));
			setMessage("Record successfully edited");
			setDeletedrecord(null);
			setId(null);
			name = null;
			accesslevel = 0;
		} 
		catch (Exception e) {
			setMessage(e.getMessage() + "\n" + ", Record could not be edited");
		}
		return "message";
	}
	
	public String deleteRecord(){
		try{
			remote.removeRole(getDeletedrecord());
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
			remote.removeRole(getDeletedrecord());
			setMessage("Record successfully deleted");
		}
		catch(Exception e){
			setMessage(e.getMessage() + "\n" + ", Record could not be deleted");
		}
		return "message";
	} 
	
	public void validateName(FacesContext context, UIComponent component, Object value)
    {
		String message = "";
		String stringToCheck = (String)value;
		stringToCheck = stringToCheck.trim();

		if (!(stringToCheck.length() > 0 && stringToCheck.length() <= 50))
		{
			((UIInput)component).setValid(false); 
			message="Error: Role Name has a maximum length of 20 characters.";
			context.addMessage(component.getClientId(), new FacesMessage(message));
		}
	}
	
	public void validateAccesslevel(FacesContext context, UIComponent component, Object value)
    {
		String message = "";
		String stringToCheck = (String)value;
		stringToCheck = stringToCheck.trim();

		if (!(stringToCheck.matches("^[0-9]+$") && stringToCheck.length() > 0 && stringToCheck.length() <= 2))
		{
			((UIInput)component).setValid(false); 
			message="Error: Access Level should be a number from 0-99 <br /> (1 = Write, 2 = Read Only, 3 = Restrictions)";
			context.addMessage(component.getClientId(), new FacesMessage(message));
		}
	}
}
