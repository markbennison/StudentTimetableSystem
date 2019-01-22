/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimetablingSystem;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import utils.BookingDetails;
import utils.ClassDetails;
import utils.RoomDetails;

/**
 *
 * @author markb
 */
@Entity
@Table(name = "BOOKINGS")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Bookings.findAll", query = "SELECT b FROM Bookings b")
	, @NamedQuery(name = "Bookings.findById", query = "SELECT b FROM Bookings b WHERE b.id = :id")
	, @NamedQuery(name = "Bookings.findByDateandtime", query = "SELECT b FROM Bookings b WHERE b.dateandtime = :dateandtime")
	, @NamedQuery(name = "Bookings.findByDuration", query = "SELECT b FROM Bookings b WHERE b.duration = :duration")})
public class Bookings implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", updatable = false, nullable = false)
	private Integer id;
	@Basic(optional = false)
    @NotNull
    @Column(name = "DATEANDTIME")
    @Temporal(TemporalType.TIMESTAMP)
	private Date dateandtime;
	@Basic(optional = false)
    @NotNull
    @Column(name = "DURATION")
	private int duration;
	@JoinColumn(name = "CLASSID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Class classid;
	@JoinColumn(name = "ROOMID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Rooms roomid;

	public Bookings() {
	}

	public Bookings(Integer id) {
		this.id = id;
	}

	public Bookings(Integer id, Date dateandtime, int duration) {
		this.id = id;
		this.dateandtime = dateandtime;
		this.duration = duration;
	}
	
	public Bookings(Date dateandtime, int duration) {
		this.dateandtime = dateandtime;
		this.duration = duration;
	}
	
	public Bookings(Class classid, Rooms roomid, Date dateandtime, int duration) {
		this.classid = classid;
		this.roomid = roomid;		
		this.dateandtime = dateandtime;
		this.duration = duration;
	}
	
	/*
	*  Constructor accepting ***Details version
	*/
	public Bookings(BookingDetails details) {
		this.id = details.getId();
		this.classid = new Class (details.getClassid());
		this.roomid = new Rooms(details.getRoomid());		
		this.dateandtime = details.getDateandtime();
		this.duration = details.getDuration();
	}
	
	/*
	*  return this as ***Details type from Library
	*/
	public BookingDetails convertToBookingDetails(){
		return new BookingDetails(this.id, this.classid.convertToClassDetails(), this.roomid.convertToRoomDetails(), this.dateandtime, this.duration);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateandtime() {
		return dateandtime;
	}

	public void setDateandtime(Date dateandtime) {
		this.dateandtime = dateandtime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	/*
	*  return ClassDetails from Library rather than Class
	*  return RoomDetails from Library rather than from Rooms
	*/
	
	public ClassDetails getClassDetails() {
		return new ClassDetails(classid.getId(), classid.getModuleDetails(), classid.getStartdate(), classid.getEnddate());
	}
	
	public RoomDetails getRoomDetails() {
		return new RoomDetails(roomid.getId(), roomid.getName(), roomid.getDescription());
	}

	public Class getClassid() {
		return classid;
	}

	public void setClassid(Class classid) {
		this.classid = classid;
	}

	public Rooms getRoomid() {
		return roomid;
	}

	public void setRoomid(Rooms roomid) {
		this.roomid = roomid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Bookings)) {
			return false;
		}
		Bookings other = (Bookings) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TimetablingSystem.Bookings[ id=" + id + " ]";
	}
	
}
