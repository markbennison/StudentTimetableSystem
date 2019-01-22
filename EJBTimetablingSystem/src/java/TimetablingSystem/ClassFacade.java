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
import utils.ClassDetails;

/**
 *
 * @author markb
 */
@Stateless (mappedName="classfacade") 
public class ClassFacade implements ClassFacadeRemote {

	@PersistenceContext(unitName = "EJBTimetablingSystemPU")
	private EntityManager em;

	public void persist(Object object) {
		em.persist(object);
	}

	private List<ClassDetails> copyClassToDetails(List<Class> classes) {
		List<ClassDetails> classList = new ArrayList<>();
		Iterator i = classes.iterator();
		while (i.hasNext()) {
			Class c = (Class) i.next();
			ClassDetails details = c.convertToClassDetails();
			
			classList.add(details);
		}     
		return classList;
	} 
	
	@Override
	public void createClass(ClassDetails details) {
		try {
			Class c = new Class(details);
			em.persist(c);
		}
		catch (Exception ex) {
			throw new EJBException(ex);
		}
	} 
 
	@Override
	public void editClass (ClassDetails details) {
		try { 
		 Class c = new Class(details);
		 em.merge(c);
		}
		catch (Exception ex) {
			throw new EJBException(ex);
		}
	} 
 
	@Override
	public void removeClass(Integer id) {
	try
	{
		Class c = em.find(Class.class, id);
		em.remove(c);
	}
	catch (Exception ex) {
			throw new EJBException(ex);
		}
	} 
 
	@Override
	public ClassDetails find(Object pk) {
		Class c = em.find(Class.class, pk);
		return c.convertToClassDetails();
	} 

	@Override
	public List<ClassDetails> findAll() {
		return copyClassToDetails(em.createQuery("select object(o) from Class as o order by o.id").getResultList());
	}
}
