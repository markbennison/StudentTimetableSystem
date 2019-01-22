/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimetablingSystem;

import java.util.List;
import javax.ejb.Remote;
import utils.RoleDetails;

/**
 *
 * @author markb
 */
@Remote
public interface RoleFacadeRemote {

	public void createRole(RoleDetails details);

	public void editRole(RoleDetails details);

	public void removeRole(Integer id);

	public RoleDetails find(Object pk);

	public List<RoleDetails> findAll();
	
}
