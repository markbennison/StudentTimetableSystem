/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimetablingSystem;

import java.util.List;
import javax.ejb.Remote;
import utils.ModuleDetails;

/**
 *
 * @author markb
 */
@Remote
public interface ModuleFacadeRemote {

	public void createModule(ModuleDetails details);

	public void editModule(ModuleDetails details);

	public void removeModule(Integer id);

	public ModuleDetails find(Object pk);

	public List<ModuleDetails> findAll();
	
}
