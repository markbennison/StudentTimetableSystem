/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author markb
 */
public class BookingDetails implements Serializable {
	private Integer id;
	private Date dateandtime;
	private int duration;
	private ClassDetails classid;
	private RoomDetails roomid;
	
	public BookingDetails(Integer id, ClassDetails classid, RoomDetails roomid, Date dateandtime, int duration) {
		this.id = id;
		this.classid = classid;
		this.roomid = roomid;		
		this.dateandtime = dateandtime;
		this.duration = duration;
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
	
}
