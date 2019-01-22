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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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
@Named(value = "timetableManagedBean")
@SessionScoped
public class TimetableManagedBean implements Serializable {

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
	
	private LocalDate now = LocalDate.now();
	private Date dateselect;
	private LocalDate weekstart;
	
	private String htmltable = "<table><tr><th>head1</th><th>head2</th></tr><tr><td>body1</td><td>body2</td></tr></table>";
	
	private ArrayList<BookingDetails> weekbookings;
	private ArrayList<BookingDetails> monday = new ArrayList<BookingDetails>();
	private ArrayList<BookingDetails> tuesday = new ArrayList<BookingDetails>();
	private ArrayList<BookingDetails> wednesday = new ArrayList<BookingDetails>();
	private ArrayList<BookingDetails> thursday = new ArrayList<BookingDetails>();
	private ArrayList<BookingDetails> friday = new ArrayList<BookingDetails>();
	private ArrayList<BookingDetails> saturday = new ArrayList<BookingDetails>();
	private ArrayList<BookingDetails> sunday = new ArrayList<BookingDetails>();
	
	private ArrayList<Integer> mondayclashes = new ArrayList<Integer>();
	private ArrayList<Integer> tuesdayclashes = new ArrayList<Integer>();
	private ArrayList<Integer> wednesdayclashes = new ArrayList<Integer>();
	private ArrayList<Integer> thursdayclashes = new ArrayList<Integer>();
	private ArrayList<Integer> fridayclashes = new ArrayList<Integer>();
	private ArrayList<Integer> saturdayclashes = new ArrayList<Integer>();
	private ArrayList<Integer> sundayclashes = new ArrayList<Integer>();
	
	/**
	 * Creates a new instance of TimetableManagedBean
	 */
	public TimetableManagedBean() {
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
	 * @return the dateselect
	 */
	public Date getDateselect() {
		if (dateselect == null){
			dateselect = Date.from(now.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		}
		weekstart = Instant.ofEpochMilli(dateselect.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		if (weekstart.getDayOfWeek().getValue() > 1){
			weekstart = weekstart.minusDays(weekstart.getDayOfWeek().getValue() - 1);
		}
		dateselect = Date.from(weekstart.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		return dateselect;
	}

	/**
	 * @param dateselect the dateselect to set
	 */
	public void setDateselect(Date dateselect) {
		weekstart = Instant.ofEpochMilli(dateselect.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		if (weekstart.getDayOfWeek().getValue() > 1){
			weekstart.minusDays(weekstart.getDayOfWeek().getValue() - 1);
		}
		this.dateselect = Date.from(weekstart.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		//this.dateselect = dateselect;
	}

	/**
	 * @return the weekstart
	 */
	public LocalDate getWeekstart() {
		return weekstart;
	}

	/**
	 * @param weekstart the weekstart to set
	 */
	public void setWeekstart(LocalDate weekstart) {
		this.weekstart = weekstart;
	}

	/**
	 * @return the htmltable
	 */
	public String getHtmltable() {
		htmltable = createHtmlTimetable();
		return htmltable;
	}

	/**
	 * @param htmltable the htmltable to set
	 */
	public void setHtmltable(String htmltable) {
		this.htmltable = htmltable;
	}

	public void resetFields(){
		setId(null);
		classid = null;
		roomid = null;
		dateandtime = null;
		duration = 0;
	}
	
	public void resetArrayLists(){
		weekbookings = new ArrayList<BookingDetails>();
		
		monday = new ArrayList<BookingDetails>();
		tuesday = new ArrayList<BookingDetails>();
		wednesday = new ArrayList<BookingDetails>();
		thursday = new ArrayList<BookingDetails>();
		friday = new ArrayList<BookingDetails>();
		saturday = new ArrayList<BookingDetails>();
		sunday = new ArrayList<BookingDetails>();
		
		mondayclashes = new ArrayList<Integer>();
		tuesdayclashes = new ArrayList<Integer>();
		wednesdayclashes = new ArrayList<Integer>();
		thursdayclashes = new ArrayList<Integer>();
		fridayclashes = new ArrayList<Integer>();
		saturdayclashes = new ArrayList<Integer>();
		sundayclashes = new ArrayList<Integer>();
	}
	
	/*
	*
	* Abandoned clash determination for better viewing
	*
	public void determineClashes(ArrayList<BookingDetails> bookings, ArrayList<Integer> clashes){
		LocalTime eventstarttime;
		LocalTime previouseventendtime;
		
		//loop from back to second item (as first item cannot be compared further)
		if (bookings != null && bookings.size() > 1){
			for (int i = bookings.size() -1; i >= 1; i--){
				eventstarttime = Instant.ofEpochMilli(bookings.get(i).getDateandtime().getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
				for (int j = i; j >= 0; j--){
					previouseventendtime = Instant.ofEpochMilli(bookings.get(j).getDateandtime().getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
					if(eventstarttime.isBefore(previouseventendtime)){
						clashes.set(i,clashes.get(i)+1);
						clashes.set(j,clashes.get(j)+1);
					}
				}
			}
		}
	}
	*/
	
	/**
	 * @return the weekbookings
	 */
	public ArrayList<BookingDetails> getWeekbookings() {
		weekstart = Instant.ofEpochMilli(getDateselect().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

		resetArrayLists();
		
		LocalDate bookinglocaldate;
		Date bookingdateonly;
		
		Date mon = Date.from(weekstart.atStartOfDay().atZone(ZoneId.systemDefault()).plusDays(0).toInstant()); 
		Date tue = Date.from(weekstart.atStartOfDay().atZone(ZoneId.systemDefault()).plusDays(1).toInstant()); 
		Date wed = Date.from(weekstart.atStartOfDay().atZone(ZoneId.systemDefault()).plusDays(2).toInstant()); 
		Date thu = Date.from(weekstart.atStartOfDay().atZone(ZoneId.systemDefault()).plusDays(3).toInstant()); 
		Date fri = Date.from(weekstart.atStartOfDay().atZone(ZoneId.systemDefault()).plusDays(4).toInstant()); 
		Date sat = Date.from(weekstart.atStartOfDay().atZone(ZoneId.systemDefault()).plusDays(5).toInstant()); 
		Date sun = Date.from(weekstart.atStartOfDay().atZone(ZoneId.systemDefault()).plusDays(6).toInstant()); 
		
		for (BookingDetails booking: getListbyuser()) {	
			bookinglocaldate = Instant.ofEpochMilli(booking.getDateandtime().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			bookingdateonly = Date.from(bookinglocaldate.atStartOfDay().atZone(ZoneId.systemDefault()).plusDays(0).toInstant()); 
			
			
			if (bookingdateonly.compareTo(mon) >= 0 && booking.getDateandtime().compareTo(sun) <= 0){
				weekbookings.add(booking);
			}
			
			if (bookingdateonly.compareTo(mon) == 0){
				monday.add(booking);
				mondayclashes.add(1);
			}
			else if (bookingdateonly.compareTo(tue) == 0){
				tuesday.add(booking);
				tuesdayclashes.add(1);
			}
			else if (bookingdateonly.compareTo(wed) == 0){
				wednesday.add(booking);
				wednesdayclashes.add(1);
			}
			else if (bookingdateonly.compareTo(thu) == 0){
				thursday.add(booking);
				thursdayclashes.add(1);
			}
			else if (bookingdateonly.compareTo(fri) == 0){
				friday.add(booking);
				fridayclashes.add(1);
			}
			else if (bookingdateonly.compareTo(sat) == 0){
				saturday.add(booking);
				saturdayclashes.add(1);
			}
			else if (bookingdateonly.compareTo(sun) == 0){
				sunday.add(booking);
				sundayclashes.add(1);
			}
		}
		return weekbookings;
	}
	
	public String updateWeek(){
		setDateselect(dateselect);
		return "timetable";
	}

	/**
	 * @param weekbookings the weekbookings to set
	 */
	public void setWeekbookings(ArrayList<BookingDetails> weekbookings) {
		this.weekbookings = weekbookings;
	}

	
	public String createDayEventMarkup(ArrayList<BookingDetails> bookings){
		String markup = "";
		int topstyle = 0;
		int heightstyle = 0;
		LocalTime eventtime;
		for (BookingDetails booking: bookings) {	
			eventtime = Instant.ofEpochMilli(booking.getDateandtime().getTime()).atZone(ZoneId.systemDefault()).toLocalTime();
			topstyle = (((eventtime.getHour() * 4) +  (eventtime.getMinute() / 15)) * 22) + 22;
			heightstyle = ((booking.getDuration() / 15) * 22) - 3;
			markup += "<div class='tt-dayevent' style='top: " + topstyle + "px; height: " + heightstyle + "px;'>" +
					booking.getId() + ": " + booking.getClassid().getModuleid().getTitle() + 
					" (" + booking.getClassid().getId() + ") " + booking.getRoomid().getName() + 
					"</div>";
		}
		
		return markup;
	}
	
	private String createHtmlTimetable(){
			
		String markup = "";
		String cssclass = "";
		int top = 0;
		LocalTime timelabel = LocalTime.MIDNIGHT;
		
		// create all rows
		markup += "<div class='tt-row tt-row-head'></div>";
		for (int row = 1; row <= 95; row++) {
			markup += "<div class='tt-row'></div>";
		}
		
		// create time labels
		markup += "<div class='tt-col tt-col-head'>";
		for (int interval = 0; interval <= 95; interval++) {
			if(interval % 4 == 0){
				cssclass = "tt-timehour";
			}
			else{
				cssclass = "tt-timeminute";
			}
			top = 11 + (interval * 22);
			timelabel = LocalTime.MIDNIGHT.plusMinutes(interval * 15);
			markup += "<div class='" + cssclass + "' style='top: " + top + "px;'>" + timelabel.format(DateTimeFormatter.ofPattern("H:mm")) + "</div>";
		}
		markup += "</div>";		
		
		// Add Monday column
		markup += "<div class='tt-col tt-monday'><div class='tt-dayhead' style='top: 0px; height:19px;'>Mon</div>";
		markup += createDayEventMarkup(monday);
		markup += "</div>";
		// Add Tuesday column
		markup += "<div class='tt-col tt-tuesday'><div class='tt-dayhead' style='top: 0px; height:19px;'>Tue</div>";
		markup += createDayEventMarkup(tuesday);
		markup += "</div>";
		// Add Wednesday column
		markup += "<div class='tt-col tt-wednesday'><div class='tt-dayhead' style='top: 0px; height:19px;'>Wed</div>";
		markup += createDayEventMarkup(wednesday);
		markup += "</div>";
		// Add Thursday column
		markup += "<div class='tt-col tt-thursday'><div class='tt-dayhead' style='top: 0px; height:19px;'>Thu</div>";
		markup += createDayEventMarkup(thursday);
		markup += "</div>";
		// Add Friday column
		markup += "<div class='tt-col tt-friday'><div class='tt-dayhead' style='top: 0px; height:19px;'>Fri</div>";
		markup += createDayEventMarkup(friday);
		markup += "</div>";
		// Add Saturday column
		markup += "<div class='tt-col tt-saturday'><div class='tt-dayhead' style='top: 0px; height:19px;'>Sat</div>";
		markup += createDayEventMarkup(saturday);
		markup += "</div>";
		// Add Sunday column
		markup += "<div class='tt-col tt-sunday'><div class='tt-dayhead' style='top: 0px; height:19px;'>Sun</div>";
		markup += createDayEventMarkup(sunday);
		markup += "</div>";
		
		return markup;
	}
	
}
