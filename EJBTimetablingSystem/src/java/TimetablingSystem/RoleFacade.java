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
import utils.RoleDetails;

/**
 *
 * @author markb
 */
@Stateless (mappedName="rolefacade") 
public class RoleFacade implements RoleFacadeRemote {

	@PersistenceContext(unitName = "EJBTimetablingSystemPU")
	private EntityManager em;

	public void persist(Object object) {
		em.persist(object);
	}

	private List<RoleDetails> copyRolesToDetails(List<Roles> roles) {
		List<RoleDetails> roleList = new ArrayList<>();
		Iterator i = roles.iterator();
		while (i.hasNext()) {
			Roles role = (Roles) i.next();
			RoleDetails details = role.convertToRoleDetails();
			
			roleList.add(details);
		}     
		return roleList;
	} 
	
	@Override
	public void createRole(RoleDetails details) {
		try {
			Roles role = new Roles(details);
			em.persist(role);
		}
		catch (Exception ex) {
			throw new EJBException(ex);
		}
	} 
 
	@Override
	public void editRole (RoleDetails details) {
		try { 
		 Roles role = new Roles(details);
		 em.merge(role);
		}
		catch (Exception ex) {
			throw new EJBException(ex);
		}
	} 
 
	@Override
	public void removeRole(Integer id) {
	try
	{
		Roles b = em.find(Roles.class, id);
		em.remove(b);
	}
	catch (Exception ex) {
			throw new EJBException(ex);
		}
	} 
 
	@Override
	public RoleDetails find(Object pk) {
		Roles role = em.find(Roles.class, pk);
		return role.convertToRoleDetails();
	} 

	@Override
	public List<RoleDetails> findAll() {
		return copyRolesToDetails(em.createQuery("select object(o) from Roles as o order by o.id").getResultList());
	}
}
