/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimetablingSystem;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import utils.RoomDetails;

/**
 *
 * @author markb
 */
@Entity
@Table(name = "ROOMS")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r")
	, @NamedQuery(name = "Rooms.findById", query = "SELECT r FROM Rooms r WHERE r.id = :id")
	, @NamedQuery(name = "Rooms.findByName", query = "SELECT r FROM Rooms r WHERE r.name = :name")
	, @NamedQuery(name = "Rooms.findByDescription", query = "SELECT r FROM Rooms r WHERE r.description = :description")})
public class Rooms implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", updatable = false, nullable = false)
	private Integer id;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "NAME")
	private String name;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "DESCRIPTION")
	private String description;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "roomid")
	private Collection<Bookings> bookingsCollection;

	public Rooms() {
	}

	public Rooms(Integer id) {
		this.id = id;
	}

	public Rooms(Integer id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public Rooms(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	/*
	*  Constructor accepting ***Details version
	*/
	public Rooms(RoomDetails details) {
		this.id = details.getId();
		this.name = details.getName();
		this.description = details.getDescription();
	}
	
	/*
	*  return this as ***Details type from Library
	*/
	public RoomDetails convertToRoomDetails(){
		return new RoomDetails(this.id, this.name, this.description);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlTransient
	public Collection<Bookings> getBookingsCollection() {
		return bookingsCollection;
	}

	public void setBookingsCollection(Collection<Bookings> bookingsCollection) {
		this.bookingsCollection = bookingsCollection;
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
		if (!(object instanceof Rooms)) {
			return false;
		}
		Rooms other = (Rooms) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TimetablingSystem.Rooms[ id=" + id + " ]";
	}
	
}
