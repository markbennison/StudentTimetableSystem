/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimetablingSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utils.ModuleDetails;

/**
 *
 * @author markb
 */
@Stateless (mappedName="modulefacade") 
public class ModuleFacade implements ModuleFacadeRemote {

	@PersistenceContext(unitName = "EJBTimetablingSystemPU")
	private EntityManager em;

	public void persist(Object object) {
		em.persist(object);
	}

	private List<ModuleDetails> copyModulesToDetails(List<Modules> modules) {
		List<ModuleDetails> moduleList = new ArrayList<>();
		Iterator i = modules.iterator();
		while (i.hasNext()) {
			Modules module = (Modules) i.next();
			ModuleDetails details = module.convertToModuleDetails();
			
			moduleList.add(details);
		}     
		return moduleList;
	} 
	
	@Override
	public void createModule(ModuleDetails details) {
		try {
			Modules module = new Modules(details);
			em.persist(module);
		}
		catch (Exception ex) {
			throw new EJBException(ex);
		}
	} 
 
	@Override
	public void editModule (ModuleDetails details) {
		try { 
		 Modules module = new Modules(details);
		 em.merge(module);
		}
		catch (Exception ex) {
			throw new EJBException(ex);
		}
	} 
 
	@Override
	public void removeModule(Integer id) {
	try
	{
		Modules m = em.find(Modules.class, id);
		em.remove(m);
	}
	catch (Exception ex) {
			throw new EJBException(ex);
		}
	} 
 
	@Override
	public ModuleDetails find(Object pk) {
		Modules module = em.find(Modules.class, pk);
		return module.convertToModuleDetails();
	} 

	@Override
	public List<ModuleDetails> findAll() {
		return copyModulesToDetails(em.createQuery("select object(o) from Modules as o order by o.id").getResultList());
	}
}
