/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimetablingSystem;

import java.util.List;
import javax.ejb.Remote;
import utils.UserDetails;

/**
 *
 * @author markb
 */
@Remote
public interface UserFacadeRemote {

	public void createUser(UserDetails details);

	public void editUser(UserDetails details);

	public void removeUser(Integer id);

	public UserDetails find(Object pk);

	public List<UserDetails> findAll();
	
	public List<UserDetails> findStaff();

	public UserDetails findUser(String username);
}
