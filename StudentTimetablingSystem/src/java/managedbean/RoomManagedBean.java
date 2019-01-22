/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import TimetablingSystem.RoomFacadeRemote; 
import javax.inject.Named; 
import javax.enterprise.context.SessionScoped; 
import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.logging.Logger; 
import javax.faces.context.FacesContext;
import javax.naming.Context; 
import javax.naming.InitialContext; 
import utils.RoomDetails;


/**
 *
 * @author markb
 */
@Named(value = "roomManagedBean")
@SessionScoped
public class RoomManagedBean implements Serializable {

	private Logger logger = Logger.getLogger("managedbeans.RoomManagedBean"); 
	private static RoomFacadeRemote remote;
	
	private ArrayList<RoomDetails> list;
	
	private Integer id;
	private String name;
	private String description;

	private Integer deletedrecord;
	
	//@ManagedProperty(value="#{messageManagedBean}")
	//private MessageManagedBean messageManagedBean;

	public RoomManagedBean() {
		try {
			Context initial = new InitialContext();
			remote = (RoomFacadeRemote) initial.lookup("roomfacade");
		} 
		catch (Exception ex) {
			System.err.println("Caught an exception:");
			ex.printStackTrace();
		} 
	} 
 
    public ArrayList<RoomDetails> getList() {
		return (ArrayList) remote.findAll();
	} 
 
    public void setList(ArrayList<RoomDetails> list) {
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
	
    public String createRecord() {
		try {
			remote.createRoom(new RoomDetails(getId(), name, description));
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
			RoomDetails temp = remote.find(getId());
			id = temp.getId();
			name = temp.getName();
			description = temp.getDescription();
			return "viewroom";
		}
		catch (Exception e) {
			logger.warning("Record could not be found");
			setMessage(e.getMessage() + "\n" + ", Record could not be found");
			return "message";
		}
	} 
	
	public String getRecord(Integer requestedId) {
		try {
			RoomDetails temp = remote.find(requestedId);
			id = temp.getId();
			name = temp.getName();
			description = temp.getDescription();
			return "viewroom";
		}
		catch (Exception e) {
			logger.warning("Record could not be found");
			setMessage(e.getMessage() + "\n" + ", Record could not be found");
			return "message";
		}
	} 
 
    public String editRecord() {
		try {
			remote.editRoom(new RoomDetails(getId(), name, description));
			setMessage("Record successfully edited");
			setDeletedrecord(null);
			setId(null);
			name = null;
			description = null;
		} 
		catch (Exception e) {
			setMessage(e.getMessage() + "\n" + ", Record could not be edited");
		}
		return "message";
	}
	
	public String deleteRecord(){
		try{
			remote.removeRoom(getDeletedrecord());
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
			remote.removeRoom(getDeletedrecord());
			setMessage("Record successfully deleted");
		}
		catch(Exception e){
			setMessage(e.getMessage() + "\n" + ", Record could not be deleted");
		}
		return "message";
	} 

} 
