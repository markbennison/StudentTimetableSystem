/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import TimetablingSystem.BookingFacadeRemote;
import TimetablingSystem.ClassFacadeRemote;
import TimetablingSystem.RoomFacadeRemote;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import static java.lang.Math.toIntExact;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import utils.BookingDetails;
import utils.ClassDetails;
import utils.RoomDetails;

/**
 *
 * @author markb
 */
@Named(value = "bookingManagedBean")
@SessionScoped
public class BookingManagedBean implements Serializable {

	private Logger logger = Logger.getLogger("managedbeans.BookingManagedBean"); 
	private static BookingFacadeRemote remote;
	
	private ArrayList<BookingDetails> list;
	private ArrayList<BookingDetails> listbyuser;
	
	private Integer id;
	private Date dateandtime;
	private int duration;
	private ClassDetails classid;
	private RoomDetails roomid;
	
	private Integer classidvalue;
	private Integer roomidvalue;
	
	private Date bookingdate;
	private Date starttime;
	private Date endtime;
	
	private Integer deletedrecord;
	
	public BookingManagedBean() {
		try {
			Context initial = new InitialContext();
			remote = (BookingFacadeRemote) initial.lookup("bookingfacade");
		} 
		catch (Exception ex) {
			System.err.println("Caught an exception:");
			ex.printStackTrace();
		} 
	}

	/**
	 * @return the list
	 */
	public ArrayList<BookingDetails> getList() {
		return (ArrayList) remote.findAll();
	}

	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<BookingDetails> list) {
		this.list = list;
	}

	/**
	 * @return the listbyuser
	 */
	public ArrayList<BookingDetails> getListbyuser() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		AuthenticationManagedBean authenticationBean = (AuthenticationManagedBean)context.getApplication().createValueBinding("#{authenticationManagedBean}").getValue(context);
		int uid = authenticationBean.getId();
		
		return (ArrayList) remote.findByUser(uid);
	}

	/**
	 * @param listbyuser the listbyuser to set
	 */
	public void setListbyuser(ArrayList<BookingDetails> listbyuser) {
		this.listbyuser = listbyuser;
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
	 * @return the dateandtime
	 */
	public Date getDateandtime() {
		return dateandtime;
	}

	/**
	 * @param dateandtime the dateandtime to set
	 */
	public void setDateandtime(Date dateandtime) {
		this.dateandtime = dateandtime;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
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
	 * @return the roomid
	 */
	public RoomDetails getRoomid() {
		return roomid;
	}

	/**
	 * @param roomid the roomid to set
	 */
	public void setRoomid(RoomDetails roomid) {
		this.roomid = roomid;
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
			remoteClass = (ClassFacadeRemote) initialClass.lookup("classfacade");
			this.classid = remoteClass.find(classidvalue);
		}
		catch (Exception e) {
			logger.warning("Class could not be found");
			setMessage(e.getMessage() + "\n" + ", Class could not be found");
		}
	}

	/**
	 * @return the roomidvalue
	 */
	public Integer getRoomidvalue() {
		if (roomid == null){
			roomidvalue = 0;
		}
		else{
			roomidvalue = roomid.getId();
		}
		return roomidvalue;
	}

	/**
	 * @param roomidvalue the roomidvalue to set
	 */
	public void setRoomidvalue(Integer roomidvalue) {
		this.roomidvalue = roomidvalue;	
		try {
			RoomFacadeRemote remoteRoom;
			Context initialRoom = new InitialContext();
			remoteRoom = (RoomFacadeRemote) initialRoom.lookup("roomfacade");	
			this.roomid = remoteRoom.find(roomidvalue);
		}
		catch (Exception e) {
			logger.warning("Room could not be found");
			setMessage(e.getMessage() + "\n" + ", Room could not be found");
		}
	}
	
	public void initialiseTimeFields(){
		bookingdate = dateandtime;
		starttime = dateandtime;
		long dateinmilliseconds = dateandtime.getTime() + (duration*60000);
		endtime = new Date(dateinmilliseconds);
	}
	
	public void controlTimeFields(){
		//merge bookingdate and starttime into a Date AND Time (to ensure getters are correct)
		long dateinmilliseconds = bookingdate.getTime() + starttime.getTime(); 
		//timezone offset...needs to be recalibrated for auto-detection of daylight savings)
		dateinmilliseconds += 3600000;
		
		//Calibrated for the users local time offset at time of use
		//Calendar cal = Calendar.getInstance();
		//dateinmilliseconds += cal.getTimeZone().getOffset(cal.getTime().getTime());
		
		dateandtime = new Date(dateinmilliseconds);
		long durationmilliseconds = endtime.getTime() - starttime.getTime();
		duration = toIntExact(durationmilliseconds / 60000);
	}

	/**
	 * @return the bookingdate
	 */
	public Date getBookingdate() {
		return bookingdate;
	}

	/**
	 * @param bookingdate the bookingdate to set
	 */
	public void setBookingdate(Date bookingdate) {
		this.bookingdate = bookingdate;
	}

	/**
	 * @return the starttime
	 */
	public Date getStarttime() {
		return starttime;
	}

	/**
	 * @param starttime the starttime to set
	 */
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	/**
	 * @return the endtime
	 */
	public Date getEndtime() {
		return endtime;
	}

	/**
	 * @param endtime the endtime to set
	 */
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
		
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
		classid = null;
		roomid = null;
		dateandtime = null;
		duration = 60;
		
		bookingdate = new Date (2019, 1, 1, 9, 0);
		starttime = bookingdate;
		long dateinmilliseconds = bookingdate.getTime() + (duration*60000);
		endtime = new Date(dateinmilliseconds);
	}
	
	public String createRecord() {
		controlTimeFields();
		try {
			remote.createBooking(new BookingDetails(getId(), classid, roomid, dateandtime, duration));
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
			BookingDetails temp = remote.find(getId());
			id = temp.getId();
			classid = temp.getClassid();
			roomid = temp.getRoomid();
			dateandtime = temp.getDateandtime();
			duration = temp.getDuration();
			initialiseTimeFields();
			return "viewbooking";
		}
		catch (Exception e) {
			logger.warning("Record could not be found");
			setMessage(e.getMessage() + "\n" + ", Record could not be found");
			return "message";
		}
	} 
	
	public String getRecord(Integer requestedId) {
		try {
			BookingDetails temp = remote.find(requestedId);
			id = temp.getId();
			classid = temp.getClassid();
			roomid = temp.getRoomid();
			dateandtime = temp.getDateandtime();
			duration = temp.getDuration();
			initialiseTimeFields();
			return "viewbooking";
		}
		catch (Exception e) {
			logger.warning("Record could not be found");
			setMessage(e.getMessage() + "\n" + ", Record could not be found");
			return "message";
		}
	} 
 
    public String editRecord() {
		controlTimeFields();
		try {
			remote.editBooking(new BookingDetails(getId(), classid, roomid, dateandtime, duration));
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
			remote.removeBooking(getDeletedrecord());
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
			remote.removeBooking(getDeletedrecord());
			setMessage("Record successfully deleted");
		}
		catch(Exception e){
			setMessage(e.getMessage() + "\n" + ", Record could not be deleted");
		}
		return "message";
	} 
	
}
