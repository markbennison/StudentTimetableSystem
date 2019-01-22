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
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import utils.ClassDetails;
import utils.ClassListDetails;
import utils.UserDetails;

/**
 *
 * @author markb
 */
@Stateless  (mappedName="classlistfacade") 
public class ClasslistFacade implements ClasslistFacadeRemote {

	@PersistenceContext(unitName = "EJBTimetablingSystemPU")
	private EntityManager em;

	public void persist(Object object) {
		em.persist(object);
	}

	private List<ClassListDetails> copyClasslistToDetails(List<Classlist> classlistrecords) {
		List<ClassListDetails> classList = new ArrayList<>();
		Iterator i = classlistrecords.iterator();
		while (i.hasNext()) {
			Classlist cl = (Classlist) i.next();
			ClassListDetails details = cl.convertToClassListDetails();
			classList.add(details);
		}     
		return classList;
	} 
	
	
	@Override
	public void createClasslist(ClassListDetails details) {
		try {
			Classlist classlist = new Classlist(details);
			em.persist(classlist);
			//em.persist(details);
		}
		catch (Exception ex) {
			throw new EJBException(ex);
		}
	} 

	@Override
	public void editClasslist (ClassListDetails details) {
		try { 
		 Classlist classlist = new Classlist(details);
		 em.merge(classlist);
		}
		catch (Exception ex) {
			throw new EJBException(ex);
		}
	} 
 
	@Override
	public void removeClasslist(ClassDetails classid, UserDetails userid) {
	try
	{
		Class c = new Class(classid);
		Users u = new Users(userid);
		
		Query query;
		query = em.createQuery("delete from Classlist c where c.class1 = :cid and c.users = :uid");
		query.setParameter("cid", c);
		query.setParameter("uid", u);
		query.executeUpdate();
	}
	catch (Exception ex) {
			throw new EJBException(ex);
		}
	} 
 
	@Override
	public ClassListDetails find(Object pk) {
		Classlist classlist = em.find(Classlist.class, pk);
		return classlist.convertToClassListDetails();
	} 

	@Override
	public List<ClassListDetails> findAll() {
		//return copyClasslistToDetails(em.createQuery("select object(o) from Classlist as o order by o.users.surname").getResultList());
		return copyClasslistToDetails(em.createQuery("select object(o) from Classlist as o").getResultList());
	}
	
	/**
	 *
	 * @param classid
	 * @return
	 */
	@Override
	public List<ClassListDetails> findByClassID(Integer classid){
		//return copyClasslistToDetails(em.createQuery("select object(o) from Classlist as o where o.classlistPK.classid = 3").getResultList());
		TypedQuery<Classlist> query;
		query = em.createQuery("select object(o) from Classlist as o where o.class1.id = :cid order by o.users.surname", Classlist.class);
		return copyClasslistToDetails(query.setParameter("cid", classid).getResultList());
	}
	
}
