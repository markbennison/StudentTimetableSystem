/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimetablingSystem;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import utils.ClassDetails;
import utils.ModuleDetails;

/**
 *
 * @author markb
 */
@Entity
@Table(name = "CLASS")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Class.findAll", query = "SELECT c FROM Class c")
	, @NamedQuery(name = "Class.findById", query = "SELECT c FROM Class c WHERE c.id = :id")
	, @NamedQuery(name = "Class.findByStartdate", query = "SELECT c FROM Class c WHERE c.startdate = :startdate")
	, @NamedQuery(name = "Class.findByEnddate", query = "SELECT c FROM Class c WHERE c.enddate = :enddate")})
public class Class implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", updatable = false, nullable = false)
	private Integer id;
	@Basic(optional = false)
    @NotNull
    @Column(name = "STARTDATE")
    @Temporal(TemporalType.DATE)
	private Date startdate;
	@Basic(optional = false)
    @NotNull
    @Column(name = "ENDDATE")
    @Temporal(TemporalType.DATE)
	private Date enddate;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "classid")
	private Collection<Bookings> bookingsCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "class1")
	private Collection<Classlist> classlistCollection;
	@JoinColumn(name = "MODULEID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Modules moduleid;

	public Class() {
	}

	public Class(Integer id) {
		this.id = id;
	}

	public Class(Integer id, Date startdate, Date enddate) {
		this.id = id;
		this.startdate = startdate;
		this.enddate = enddate;
	}
	
	public Class(Date startdate, Date enddate) {
		this.startdate = startdate;
		this.enddate = enddate;
	}
	
	public Class(Modules moduleid, Date startdate, Date enddate) {
		this.moduleid = moduleid;
		this.startdate = startdate;
		this.enddate = enddate;
	}

	/*
	*  Constructor accepting ***Details version
	*/
	public Class(ClassDetails details) {
		this.id = details.getId();
		this.moduleid = new Modules(details.getModuleid());
		this.startdate = details.getStartdate();
		this.enddate = details.getEnddate();
	}
	/*
	*  return this as ***Details type from Library
	*/
	public ClassDetails convertToClassDetails(){
		return new ClassDetails(this.id, this.moduleid.convertToModuleDetails(), this.startdate, this.enddate);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@XmlTransient
	public Collection<Bookings> getBookingsCollection() {
		return bookingsCollection;
	}

	public void setBookingsCollection(Collection<Bookings> bookingsCollection) {
		this.bookingsCollection = bookingsCollection;
	}

	@XmlTransient
	public Collection<Classlist> getClasslistCollection() {
		return classlistCollection;
	}

	public void setClasslistCollection(Collection<Classlist> classlistCollection) {
		this.classlistCollection = classlistCollection;
	}
		
	/*
	*  return ModuleDetails from Library rather than Modules
	*/
	
	public ModuleDetails getModuleDetails(){
		return new ModuleDetails (moduleid.getId(), moduleid.getTitle(), moduleid.getModulecode(), moduleid.getLeaderDetails());
	}

	public Modules getModuleid() {
		return moduleid;
	}

	public void setModuleid(Modules moduleid) {
		this.moduleid = moduleid;
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
		if (!(object instanceof Class)) {
			return false;
		}
		Class other = (Class) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TimetablingSystem.Class[ id=" + id + " ]";
	}
	
}
