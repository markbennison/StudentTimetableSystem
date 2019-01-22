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
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import utils.RoleDetails;
import utils.UserDetails;

/**
 *
 * @author markb
 */
@Named(value = "authenticationManagedBean")
@SessionScoped
public class AuthenticationManagedBean implements Serializable {
	
	
	private Logger logger = Logger.getLogger("managedbeans.UserManagedBean");
	private static UserFacadeRemote remote;
		
	private Integer id;
	private String username;
    private String password;
	private String forename;
	private String surname;
	private RoleDetails roleid;
	private Integer roleidvalue;
	private String usernameattempt;
	private String passwordattempt;

	private boolean credentialsOK = false;
	
	public AuthenticationManagedBean() {
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
	 * @return the usernameattempt
	 */
	public String getUsernameattempt() {
		return usernameattempt;
	}

	/**
	 * @param usernameattempt the usernameattempt to set
	 */
	public void setUsernameattempt(String usernameattempt) {
		this.usernameattempt = usernameattempt;
	}

	/**
	 * @return the passwordattempt
	 */
	public String getPasswordattempt() {
		return passwordattempt;
	}

	/**
	 * @param passwordattempt the passwordattempt to set
	 */
	public void setPasswordattempt(String passwordattempt) {
		try{
            byte[] hash = MessageDigest.getInstance("SHA-256").digest(passwordattempt.getBytes(StandardCharsets.UTF_8));
            this.passwordattempt = Base64.getEncoder().encodeToString(hash);
        }
        catch (NoSuchAlgorithmException ex){
            this.passwordattempt = "";
            Logger.getLogger(AuthenticationManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	/**
	 * @return the credentialsOK
	 */
	public boolean isCredentialsOK() {
		return credentialsOK;
	}

	/**
	 * @param credentialsOK the credentialsOK to set
	 */
	public void setCredentialsOK(boolean credentialsOK) {
		this.credentialsOK = credentialsOK;
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

	public void resetFields(){
		setId(null);
		username = null;
		password = null;
		forename = null;
		surname = null;
		roleid = null;
		roleidvalue = 0;
		setUsernameattempt(null);
		passwordattempt = null;
		credentialsOK = false;
	}
	
	public String signIn() {
		credentialsOK = false;
		try {
			UserDetails temp = remote.findUser(getUsernameattempt());
			id = temp.getId();
			username = temp.getUsername();
			password = temp.getPassword();
			forename = temp.getForename();
			surname = temp.getSurname();
			roleid = temp.getRoleid();
			
			if (passwordattempt.equals(password)){
				credentialsOK = true;
				password = null;
				passwordattempt = null;
			}
		}
		catch (Exception e) {
			logger.warning("Record could not be found");
			setMessage(e.getMessage() + "\n" + ", Record could not be found");
			return "message";
		}
		
		if (credentialsOK){
            return "home";
        }
		else {
            resetFields();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login credentials are not correct"));
            return "login";
        }	
	} 
	
	public String signOut()
    {
        resetFields();
        return "home";
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
	
	public void validatePassword(FacesContext context, UIComponent component, Object value)
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
	
	public String getLoginControlValue() {
		if(isCredentialsOK()){
			return "Logout";
		}else{
			return "Login";
		}
	}
	
	public String getLoginControlAction() {
		if(isCredentialsOK()){
			return signOut();
		}else{
			return "login";
		}
	}
	
	public String getUserMenuVisibility(){
		if(isCredentialsOK()){
			return "";
		}else{
			return "hidden";
		}
	}
	
	public String getStaffMenuVisibility(){
		int role = getRoleidvalue();
		if(role == 2){
			return "";
		}else{
			return "hidden";
		}
	}
	
	public String getAdminMenuVisibility(){
		int role = getRoleidvalue();
		if(role == 1){
			return "";
		}else{
			return "hidden";
		}
	}
	
}
