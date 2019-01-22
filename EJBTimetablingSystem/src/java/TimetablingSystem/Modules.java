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
import utils.ModuleDetails;
import utils.UserDetails;

/**
 *
 * @author markb
 */
@Entity
@Table(name = "MODULES")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Modules.findAll", query = "SELECT m FROM Modules m")
	, @NamedQuery(name = "Modules.findById", query = "SELECT m FROM Modules m WHERE m.id = :id")
	, @NamedQuery(name = "Modules.findByTitle", query = "SELECT m FROM Modules m WHERE m.title = :title")
	, @NamedQuery(name = "Modules.findByModulecode", query = "SELECT m FROM Modules m WHERE m.modulecode = :modulecode")})
public class Modules implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", updatable = false, nullable = false)
	private Integer id;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TITLE")
	private String title;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MODULECODE")
	private String modulecode;
	@JoinColumn(name = "LEADER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
	private Users leader;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "moduleid")
	private Collection<Class> classCollection;

	public Modules() {
	}

	public Modules(Integer id) {
		this.id = id;
	}

	public Modules(Integer id, String title, String modulecode) {
		this.id = id;
		this.title = title;
		this.modulecode = modulecode;
	}
	
	public Modules(String title, String modulecode) {
		this.title = title;
		this.modulecode = modulecode;
	}
	
	public Modules(String title, String modulecode, Users leader) {
		this.title = title;
		this.modulecode = modulecode;
		this.leader = leader;
	}
	
	/*
	*  Constructor accepting ***Details version
	*/
	public Modules(ModuleDetails details) {
		this.id = details.getId();
		this.title = details.getTitle();
		this.modulecode = details.getModulecode();
		this.leader = new Users(details.getLeader());
	}
	
	/*
	*  return this as ***Details type from Library
	*/
	public ModuleDetails convertToModuleDetails(){
		return new ModuleDetails(this.id, this.title, this.modulecode, this.leader.convertToUserDetails());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getModulecode() {
		return modulecode;
	}

	public void setModulecode(String modulecode) {
		this.modulecode = modulecode;
	}

	public UserDetails getLeaderDetails(){
		return new UserDetails(leader.getId(), leader.getUsername(), leader.getForename(), leader.getSurname(), leader.getRoleDetails());
	}
	
	/*
	*  return UserDetails from Library rather than Users
	*/
	
	public Users getLeader() {
		return leader;
	}

	public void setLeader(Users leader) {
		this.leader = leader;
	}

	@XmlTransient
	public Collection<Class> getClassCollection() {
		return classCollection;
	}

	public void setClassCollection(Collection<Class> classCollection) {
		this.classCollection = classCollection;
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
		if (!(object instanceof Modules)) {
			return false;
		}
		Modules other = (Modules) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TimetablingSystem.Modules[ id=" + id + " ]";
	}
	
}
