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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import utils.UserDetails;
import utils.RoleDetails;

/**
 *
 * @author markb
 */
@Entity
@Table(name = "USERS")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
	, @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id")
	, @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username")
	, @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password")
	, @NamedQuery(name = "Users.findByForename", query = "SELECT u FROM Users u WHERE u.forename = :forename")
	, @NamedQuery(name = "Users.findBySurname", query = "SELECT u FROM Users u WHERE u.surname = :surname")})
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", updatable = false, nullable = false)
	private Integer id;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USERNAME", unique = true, nullable = false)
	private String username;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PASSWORD")
	private String password;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "FORENAME")
	private String forename;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "SURNAME")
	private String surname;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "leader")
	private Collection<Modules> modulesCollection;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
	private Collection<Classlist> classlistCollection;
	@JoinColumn(name = "ROLEID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Roles roleid;

	public Users() {
	}

	public Users(Integer id) {
		this.id = id;
	}

	public Users(Integer id, String username, String password, String forename, String surname) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.forename = forename;
		this.surname = surname;
	}
	
	public Users(String username, String password, String forename, String surname) {
		this.username = username;
		this.password = password;
		this.forename = forename;
		this.surname = surname;
	}

	public Users(String username, String password, String forename, String surname, Roles roleid) {
		this.username = username;
		this.password = password;
		this.forename = forename;
		this.surname = surname;
		this.roleid = roleid;
	}
		
	/*
	*  Constructor accepting ***Details version
	*/
	public Users(UserDetails details) {
		this.id = details.getId();
		this.username = details.getUsername();
		this.password = details.getPassword();
		this.forename = details.getForename();
		this.surname = details.getSurname();
		this.roleid = new Roles(details.getRoleid());
	}
	
	/*
	*  return this as ***Details type from Library
	*/
	public UserDetails convertToUserDetails(){
		return new UserDetails(this.id, this.username, this.password, this.forename, this.surname, this.roleid.convertToRoleDetails());
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@XmlTransient
	public Collection<Modules> getModulesCollection() {
		return modulesCollection;
	}

	public void setModulesCollection(Collection<Modules> modulesCollection) {
		this.modulesCollection = modulesCollection;
	}

	@XmlTransient
	public Collection<Classlist> getClasslistCollection() {
		return classlistCollection;
	}

	public void setClasslistCollection(Collection<Classlist> classlistCollection) {
		this.classlistCollection = classlistCollection;
	}
	
	/*
	*  return RoleDetails from Library rather than Roles
	*/

	public RoleDetails getRoleDetails() {
		return new RoleDetails(roleid.getId(), roleid.getName(), roleid.getAccesslevel());
	}
	
	public Roles getRoleid() {
		return roleid;
	}

	public void setRoleid(Roles roleid) {
		this.roleid = roleid;
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
		if (!(object instanceof Users)) {
			return false;
		}
		Users other = (Users) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TimetablingSystem.Users[ id=" + id + " ]";
	}
	
}
