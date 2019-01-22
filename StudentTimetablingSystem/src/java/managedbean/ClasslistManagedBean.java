/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import TimetablingSystem.ClassFacadeRemote;
import TimetablingSystem.ClasslistFacadeRemote;
import TimetablingSystem.UserFacadeRemote;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import utils.ClassDetails;
import utils.ClassListDetails;
import utils.UserDetails;

/**
 *
 * @author markb
 */
@Named(value = "classlistManagedBean")
@SessionScoped
public class ClasslistManagedBean implements Serializable {

	private Logger logger = Logger.getLogger("managedbeans.ClasslistManagedBean"); 
	private static ClasslistFacadeRemote remote;
	
	private ArrayList<ClassListDetails> list;
	private ArrayList<ClassListDetails> classlist;
	
	private ClassDetails classid;
	private UserDetails userid;
	
	private Integer classidvalue;
	private Integer useridvalue;

	private Integer deletedrecord;
	
	/**
	 * Creates a new instance of ClasslistManagedBean
	 */
	public ClasslistManagedBean() {
		try {
			Context initial = new InitialContext();
			remote = (ClasslistFacadeRemote) initial.lookup("classlistfacade");
		} 
		catch (Exception ex) {
			System.err.println("Caught an exception:");
			ex.printStackTrace();
		} 
	}

	/**
	 * @return the list
	 */
	public ArrayList<ClassListDetails> getList() {
		//return (ArrayList) remotelist.findByClassID(id);
		return (ArrayList) remote.findAll();
	}

	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<ClassListDetails> list) {
		this.list = list;
	}

	/**
	 * @return the classlist
	 */
	public ArrayList<ClassListDetails> getClasslist() {
		FacesContext context = FacesContext.getCurrentInstance();
		ClassManagedBean classBean = (ClassManagedBean)context.getApplication().createValueBinding("#{classManagedBean}").getValue(context);
		int cid = classBean.getId();	
		return (ArrayList) remote.findByClassID(cid);
	}

	/**
	 * @param classlist the classlist to set
	 */
	public void setClasslist(ArrayList<ClassListDetails> classlist) {
		this.classlist = classlist;
	}
	
	public ClassDetails getInstanceOfClassID(){
		FacesContext context = FacesContext.getCurrentInstance();
		ClassManagedBean classBean = (ClassManagedBean)context.getApplication().createValueBinding("#{classManagedBean}").getValue(context);
		return new ClassDetails(classBean.getId(), classBean.getModuleid(), classBean.getStartdate(), classBean.getEnddate());
	}

	/**
	 * @return the classid
	 */
	public ClassDetails getClassid() {
		return classid;
	}

	/**
	 * @param classid the classid to set
	 */
	public void setClassid(ClassDetails classid) {
		this.classid = classid;
	}

	/**
	 * @return the userid
	 */
	public UserDetails getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(UserDetails userid) {		
		this.userid = userid;
	}

	/**
	 * @return the classidvalue
	 */
	public Integer getClassidvalue() {
		if (classid == null){
			classidvalue = 0;
		}
		else{
			classidvalue = classid.getId();
		}	
		return classidvalue;
	}

	/**
	 * @param classidvalue the classidvalue to set
	 */
	public void setClassidvalue(Integer classidvalue) {
		this.classidvalue = classidvalue;
		try {
			ClassFacadeRemote remoteClass;
			Context initialClass = new InitialContext();
			remoteClass = (ClassFacadeRemote) initialClass.lookup("userfacade");
			this.classid = remoteClass.find(classidvalue);
		}
		catch (Exception e) {
			logger.warning("ClassID could not be found");
			setMessage(e.getMessage() + "\n" + ", ClassID could not be found");
		}
		this.classidvalue = classidvalue;
	}

	/**
	 * @return the useridvalue
	 */
	public Integer getUseridvalue() {
		if (userid == null){
			useridvalue = 0;
		}
		else{
			useridvalue = userid.getId();
		}	
		return useridvalue;
	}

	/**
	 * @param useridvalue the useridvalue to set
	 */
	public void setUseridvalue(Integer useridvalue) {
		this.useridvalue = useridvalue;
		try {
			UserFacadeRemote remoteUser;
			Context initialUser = new InitialContext();
			remoteUser = (UserFacadeRemote) initialUser.lookup("userfacade");
			this.userid = remoteUser.find(useridvalue);
		}
		catch (Exception e) {
			logger.warning("User could not be found");
			setMessage(e.getMessage() + "\n" + ", User could not be found");
		}
		
		this.useridvalue = useridvalue;
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
		classid = null;
		userid = null;
	}
	
	
	public String addClassMember() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			ClassManagedBean classBean = (ClassManagedBean)context.getApplication().createValueBinding("#{classManagedBean}").getValue(context);
			ClassDetails currentclass = new ClassDetails(classBean.getId(), classBean.getModuleid(), classBean.getStartdate(), classBean.getEnddate());
			//int cid = classBean.getId();
		
			remote.createClasslist(new ClassListDetails(currentclass, userid));
			setMessage("Record successfully added");
			//classlist = null;
		} 
		catch (Exception e) {
			logger.warning("Record could not be created");
			setMessage(e.getMessage() + "\n" + ", Record could not be created");
			return "message";
		}
		return "editclass";
	} 
	
	public String removeClassMember(Integer requestedId) {
		try {
			deletedrecord = requestedId;
			setUseridvalue(requestedId);
			//ClassListDetails cld = new ClassListDetails(getInstanceOfClassID(), this.userid);
			
			remote.removeClasslist(getInstanceOfClassID(), this.userid);
			setMessage("Record successfully deleted");
			resetFields();
		}
		catch(Exception e){
			setMessage(e.getMessage() + "\n" + ", Record could not be deleted");
		}
		return "message";
	} 
	
}
