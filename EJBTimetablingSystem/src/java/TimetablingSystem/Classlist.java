/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimetablingSystem;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import utils.ClassListDetails;

/**
 *
 * @author markb
 */
@Entity
@Table(name = "CLASSLIST")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Classlist.findAll", query = "SELECT c FROM Classlist c")
	, @NamedQuery(name = "Classlist.findByClassid", query = "SELECT c FROM Classlist c WHERE c.classlistPK.classid = :classid")
	, @NamedQuery(name = "Classlist.findByUserid", query = "SELECT c FROM Classlist c WHERE c.classlistPK.userid = :userid")
	, @NamedQuery(name = "Classlist.findByRole", query = "SELECT c FROM Classlist c WHERE c.role = :role")})
public class Classlist implements Serializable {

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected ClasslistPK classlistPK;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ROLE")
	private String role;
	@JoinColumn(name = "CLASSID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
	private Class class1;
	@JoinColumn(name = "USERID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
	private Users users;

	public Classlist() {
	}

	public Classlist(ClasslistPK classlistPK) {
		this.classlistPK = classlistPK;
	}

	public Classlist(ClasslistPK classlistPK, String role) {
		this.classlistPK = classlistPK;
		this.role = role;
	}

	public Classlist(int classid, int userid) {
		this.classlistPK = new ClasslistPK(classid, userid);
	}
	
	public Classlist(int classid, int userid, String role) {
		this.classlistPK = new ClasslistPK(classid, userid);
		this.role = role;
	}
	
	/*
	*  Constructor accepting ***Details version (default to "student")
	*/
	public Classlist(ClassListDetails details) {
		this.class1 = new Class (details.getClassid());
		this.users = new Users (details.getUserid());
		//this.role = "student";
		this.classlistPK = new ClasslistPK(details.getClassid().getId(), details.getUserid().getId());
		this.role = details.getRole();
	}

	/*
	*  return this as ***Details type from Library
	*/
	public ClassListDetails convertToClassListDetails(){
		return new ClassListDetails(this.class1.convertToClassDetails(), this.users.convertToUserDetails(), this.role);
	}

	public ClasslistPK getClasslistPK() {
		return classlistPK;
	}

	public void setClasslistPK(ClasslistPK classlistPK) {
		this.classlistPK = classlistPK;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Class getClass1() {
		return class1;
	}

	public void setClass1(Class class1) {
		this.class1 = class1;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (classlistPK != null ? classlistPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Classlist)) {
			return false;
		}
		Classlist other = (Classlist) object;
		if ((this.classlistPK == null && other.classlistPK != null) || (this.classlistPK != null && !this.classlistPK.equals(other.classlistPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TimetablingSystem.Classlist[ classlistPK=" + classlistPK + " ]";
	}
	
}
