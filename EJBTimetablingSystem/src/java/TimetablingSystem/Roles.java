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
import utils.RoleDetails;

/**
 *
 * @author markb
 */
@Entity
@Table(name = "ROLES")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r")
	, @NamedQuery(name = "Roles.findById", query = "SELECT r FROM Roles r WHERE r.id = :id")
	, @NamedQuery(name = "Roles.findByName", query = "SELECT r FROM Roles r WHERE r.name = :name")
	, @NamedQuery(name = "Roles.findByAccesslevel", query = "SELECT r FROM Roles r WHERE r.accesslevel = :accesslevel")})
public class Roles implements Serializable {

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
    @Column(name = "ACCESSLEVEL")
	private int accesslevel;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "roleid")
	private Collection<Users> usersCollection;

	public Roles() {
	}

	public Roles(Integer id) {
		this.id = id;
	}

	public Roles(Integer id, String name, int accesslevel) {
		this.id = id;
		this.name = name;
		this.accesslevel = accesslevel;
	}
	
	public Roles(String name, int accesslevel) {
		this.name = name;
		this.accesslevel = accesslevel;
	}
	
	/*
	*  Constructor accepting ***Details version
	*/
	public Roles(RoleDetails details) {
		this.id = details.getId();
		this.name = details.getName();
		this.accesslevel = details.getAccesslevel();
	}
	
	/*
	*  return this as ***Details type from Library
	*/
	public RoleDetails convertToRoleDetails(){
		return new RoleDetails(this.id, this.name, this.accesslevel);
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

	public int getAccesslevel() {
		return accesslevel;
	}

	public void setAccesslevel(int accesslevel) {
		this.accesslevel = accesslevel;
	}

	@XmlTransient
	public Collection<Users> getUsersCollection() {
		return usersCollection;
	}

	public void setUsersCollection(Collection<Users> usersCollection) {
		this.usersCollection = usersCollection;
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
		if (!(object instanceof Roles)) {
			return false;
		}
		Roles other = (Roles) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TimetablingSystem.Roles[ id=" + id + " ]";
	}
	
}
