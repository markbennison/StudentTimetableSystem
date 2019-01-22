/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimetablingSystem;

import java.util.List;
import javax.ejb.Remote;
import utils.ClassDetails;

/**
 *
 * @author markb
 */
@Remote
public interface ClassFacadeRemote {

	public void createClass(ClassDetails details);

	public void editClass(ClassDetails details);

	public void removeClass(Integer id);

	public ClassDetails find(Object pk);

	public List<ClassDetails> findAll();
	
}
