/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimetablingSystem;

import java.util.List;
import javax.ejb.Remote;
import utils.ClassDetails;
import utils.ClassListDetails;
import utils.UserDetails;

/**
 *
 * @author markb
 */
@Remote
public interface ClasslistFacadeRemote {

	public void createClasslist(ClassListDetails details);

	public void editClasslist(ClassListDetails details);

	public void removeClasslist(ClassDetails classid, UserDetails userid);

	public ClassListDetails find(Object pk);

	public List<ClassListDetails> findAll();

	/**
	 *
	 * @param classid
	 * @return
	 */
	public List<ClassListDetails> findByClassID(Integer classid);
	
}
