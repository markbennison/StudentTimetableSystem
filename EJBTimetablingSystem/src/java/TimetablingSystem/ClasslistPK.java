/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimetablingSystem;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author markb
 */
@Embeddable
public class ClasslistPK implements Serializable {

	@Basic(optional = false)
    @NotNull
    @Column(name = "CLASSID")
	private int classid;
	@Basic(optional = false)
    @NotNull
    @Column(name = "USERID")
	private int userid;

	public ClasslistPK() {
	}

	public ClasslistPK(int classid, int userid) {
		this.classid = classid;
		this.userid = userid;
	}

	public int getClassid() {
		return classid;
	}

	public void setClassid(int classid) {
		this.classid = classid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (int) classid;
		hash += (int) userid;
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof ClasslistPK)) {
			return false;
		}
		ClasslistPK other = (ClasslistPK) object;
		if (this.classid != other.classid) {
			return false;
		}
		if (this.userid != other.userid) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "TimetablingSystem.ClasslistPK[ classid=" + classid + ", userid=" + userid + " ]";
	}
	
}
