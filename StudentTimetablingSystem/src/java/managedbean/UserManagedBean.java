/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import TimetablingSystem.RoleFacadeRemote;
import TimetablingSystem.UserFacadeRemote; 
import javax.inject.Named; 
import javax.enterprise.context.SessionScoped; 
import java.io.Serializable; 
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList; 
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger; 
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.naming.Context; 
import javax.naming.InitialContext; 
import utils.UserDetails;
import utils.RoleDetails;

/**
 *
 * @author markb
 */
@Named(value = "userManagedBean")
@SessionScoped
public class UserManagedBean implements Serializable {

	private Logger logger = Logger.getLogger("managedbeans.UserManagedBean");
	private static UserFacadeRemote remote;
	
	private ArrayList<UserDetails> list;
	private ArrayList<UserDetails> stafflist;
		
	private Integer id;
	private String username;
    private String password;
	private String forename;
	private String surname;
	private RoleDetails roleid;
	private Integer roleidvalue;
	
	private String passwordplain;
	private String passwordcheck;

	private Integer deletedrecord;
 
	/**
	 * Creates a new instance of UserManagedBean
	 */
	public UserManagedBean() {
		try {
			Context initial = new InitialContext();
			remote = (UserFacadeRemote) initial.lookup("userfacade");
		} 
		catch (Exception ex) {
			System.err.println("Caught an exception:");
			ex.printStackTrace();
		} 
	}

	/**
	 * @return the list
	 */
	public ArrayList<UserDetails> getList() {
		return (ArrayList) remote.findAll();
	}

	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<UserDetails> list) {
		this.list = list;
	}

	/**
	 * @return the stafflist
	 */
	public ArrayList<UserDetails> getStafflist() {
		return (ArrayList) remote.findStaff();
	}

	/**
	 * @param stafflist the stafflist to set
	 */
	public void setStafflist(ArrayList<UserDetails> stafflist) {
		this.stafflist = stafflist;
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
	public void setPassword(String passwordplain) {
		try
        {
            byte[] hash = MessageDigest.getInstance("SHA-256").digest(passwordplain.getBytes(StandardCharsets.UTF_8));
            this.password = Base64.getEncoder().encodeToString(hash);
        }
        catch (NoSuchAlgorithmException ex)
        {
            this.passwordplain = "";
			this.passwordcheck = "";
            Logger.getLogger(AuthenticationManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
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
	public void setRoleid(Integer roleidvalue) {
		try {
			RoleFacadeRemote remoteRole;
			Context initialRole = new InitialContext();
			remoteRole = (RoleFacadeRemote) initialRole.lookup("rolefacade");
		
			RoleDetails temp = remoteRole.find(roleidvalue);
			//this.roleid = temp;
			this.roleid.setId(temp.getId());
			this.roleid.setName(temp.getName());
			this.roleid.setAccesslevel(temp.getAccesslevel());
		}
		catch (Exception e) {
			logger.warning("Role could not be found");
			setMessage(e.getMessage() + "\n" + ", Role could not be found");
		}
	}

	/**
	 * @return the roleidvalue
	 */
	public Integer getRoleidvalue() {
		if (roleid == null){
			roleidvalue = 0;
		}
		else{
			roleidvalue = roleid.getId();
		}
		return roleidvalue;
	}

	/**
	 * @param roleidvalue the roleidvalue to set
	 */
	public void setRoleidvalue(Integer roleidvalue) {
		this.roleidvalue = roleidvalue;
		
		try {
			RoleFacadeRemote remoteRole;
			Context initialRole = new InitialContext();
			remoteRole = (RoleFacadeRemote) initialRole.lookup("rolefacade");
			roleid = remoteRole.find(roleidvalue);
		}
		catch (Exception e) {
			logger.warning("Role could not be found");
			setMessage(e.getMessage() + "\n" + ", Role could not be found");
		}
	}
	
	/**
	 * @return the passwordplain
	 */
	public String getPasswordplain() {
		return passwordplain;
	}

	/**
	 * @param passwordplain the passwordplain to set
	 */
	public void setPasswordplain(String passwordplain) {
		this.passwordplain = passwordplain;
	}

	/**
	 * @return the passwordcheck
	 */
	public String getPasswordcheck() {
		return passwordcheck;
	}

	/**
	 * @param passwordcheck the passwordcheck to set
	 */
	public void setPasswordcheck(String passwordcheck) {
		this.passwordcheck = passwordcheck;
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
		username = null;
		password = null;
		passwordplain = null;
		passwordcheck = null;
		forename = null;
		surname = null;
		roleid = null;
	}
	
	public String createRecord() {
		if (passwordplain.equals(passwordcheck)) {
			this.setPassword(passwordplain);
					
			try {			
				remote.createUser(new UserDetails(getId(), username, password, forename, surname, roleid));
				setMessage("Record successfully added");
			} 
			catch (Exception e) {
				logger.warning("Record could not be created");
				setMessage(e.getMessage() + "\n" + ", Record could not be created");
			}
		}
		else{
			setMessage("Passwords do not match");
		}
		resetFields();
		return "message";
	} 
 
    public String getRecord() {
		try {
			UserDetails temp = remote.find(getId());
			id = temp.getId();
			username = temp.getUsername();
			password = temp.getPassword();
			forename = temp.getForename();
			surname = temp.getSurname();
			roleid = temp.getRoleid();
			return "viewuser";
		}
		catch (Exception e) {
			logger.warning("Record could not be found");
			setMessage(e.getMessage() + "\n" + ", Record could not be found");
			return "message";
		}
	} 
	
	public String getRecord(Integer requestedId) {
		try {
			UserDetails temp = remote.find(requestedId);
			id = temp.getId();
			username = temp.getUsername();
			password = temp.getPassword();
			forename = temp.getForename();
			surname = temp.getSurname();
			roleid = temp.getRoleid();
			return "viewuser";
		}
		catch (Exception e) {
			logger.warning("Record could not be found");
			setMessage(e.getMessage() + "\n" + ", Record could not be found");
			return "message";
		}
	} 
 
    public String editRecord() {
		if (passwordplain.equals(passwordcheck)) {
			this.setPassword(passwordplain);
			
			try {
				remote.editUser(new UserDetails(getId(), username, password, forename, surname, roleid));
				setMessage("Record successfully edited");
				resetFields();
			} 
			catch (Exception e) {
				setMessage(e.getMessage() + "\n" + ", Record could not be edited");
			}
		}
		else{
			setMessage("Passwords do not match");
		}
		return "message";
	}
	
	public String deleteRecord(){
		try{
			remote.removeUser(getDeletedrecord());
			setMessage("Record successfully deleted");
		}
		catch(Exception e){
			setMessage(e.getMessage() + "\n" + ", Record could not be deleted");
		}
		resetFields();
		return "message";
	} 
	
	public String deleteRecord(Integer requestedId){
		try{
			deletedrecord = requestedId;
			remote.removeUser(getDeletedrecord());
			setMessage("Record successfully deleted");
		}
		catch(Exception e){
			setMessage(e.getMessage() + "\n" + ", Record could not be deleted");
		}
		resetFields();
		return "message";
	} 
	
	public void validateUsername(FacesContext context, UIComponent component, Object value)
    {
		String message = "";
		String stringToCheck = (String)value;
		stringToCheck = stringToCheck.trim();

		if (!(stringToCheck.matches("^[a-zA-Z]+$") && stringToCheck.length() > 0 && stringToCheck.length() <= 20))
		{
			((UIInput)component).setValid(false); 
			message="Error: Username can only use letters - no spaces, numbers or special characters. Maximum length is 20 characters.";
			context.addMessage(component.getClientId(), new FacesMessage(message));
		}
	}
	
	public void validatePasswordPlain(FacesContext context, UIComponent component, Object value)
    {
		String message = "";
		String stringToCheck = (String)value;
		stringToCheck = stringToCheck.trim();

		if (!(stringToCheck.length() > 0 && stringToCheck.length() <= 20))
		{
			((UIInput)component).setValid(false); 
			message="Error: Password has a maximum length of 20 characters.";
			context.addMessage(component.getClientId(), new FacesMessage(message));
		}
	}
		
	public void validatePasswordCheck(ComponentSystemEvent event)
    {
		FacesContext fc = FacesContext.getCurrentInstance();

		UIComponent components = event.getComponent();

		// get password
		UIInput uiInputPassword = (UIInput) components.findComponent("passwordplain");
		String passwordplain = uiInputPassword.getLocalValue() == null ? ""
			: uiInputPassword.getLocalValue().toString();
		String passwordId = uiInputPassword.getClientId();

		// get confirm password
		UIInput uiInputConfirmPassword = (UIInput) components.findComponent("passwordcheck");
		String passwordcheck= uiInputConfirmPassword.getLocalValue() == null ? ""
			: uiInputConfirmPassword.getLocalValue().toString();

		// Let required="true" do its job.
		if (passwordplain.isEmpty() || passwordcheck.isEmpty()) 
		{
			return;
		}

		if (!passwordplain.equals(passwordcheck)) 
		{
			FacesMessage msg = new FacesMessage("Passwords must match");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(passwordId, msg);
			fc.renderResponse();
		}
	}
	
		
	public void validateForename(FacesContext context, UIComponent component, Object value)
	{
		String message = "";
		String stringToCheck = (String)value;
		stringToCheck = stringToCheck.trim();

		if (!(stringToCheck.matches("^[a-zA-Z]+([a-zA-Z -][a-zA-Z])*$") && stringToCheck.length() > 0 && stringToCheck.length() <= 20))
		{
			((UIInput)component).setValid(false); 
			message="Error: Forename can only use letters, spaces and dashes. Maximum length is 20 characters.";
			context.addMessage(component.getClientId(), new FacesMessage(message));
		}
	}
		
	public void validateSurname(FacesContext context, UIComponent component, Object value)
	{
		String message = "";
		String stringToCheck = (String)value;
		stringToCheck = stringToCheck.trim();

		if (!(stringToCheck.matches("^[a-zA-Z]+([a-zA-Z -][a-zA-Z])*$") && stringToCheck.length() > 0 && stringToCheck.length() <= 20))
		{
			((UIInput)component).setValid(false); 
			message="Error: Surname can only use letters, spaces and dashes. Maximum length is 20 characters.";
			context.addMessage(component.getClientId(), new FacesMessage(message));
		}
	}
	
}
