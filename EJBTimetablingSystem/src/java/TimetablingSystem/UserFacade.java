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
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import utils.UserDetails;

/**
 *
 * @author markb
 */
@Stateless (mappedName="userfacade") 
public class UserFacade implements UserFacadeRemote {

	@PersistenceContext(unitName = "EJBTimetablingSystemPU")
	private EntityManager em;

	public void persist(Object object) {
		em.persist(object);
	}

	private List<UserDetails> copyUsersToDetails(List<Users> accounts) {
		List<UserDetails> accountList = new ArrayList<>();
		Iterator i = accounts.iterator();
		while (i.hasNext()) {
			Users user = (Users) i.next();
			UserDetails details = user.convertToUserDetails();
			
			accountList.add(details);
		}     
		return accountList;
	} 
	
	@Override
	public void createUser(UserDetails details) {
		try {
			Users user = new Users(details);
			em.persist(user);
		}
		catch (Exception ex) {
			throw new EJBException(ex);
		}
	} 
 
	@Override
	public void editUser (UserDetails details) {
		try { 
		 Users user = new Users(details);
		 em.merge(user);
		}
		catch (Exception ex) {
			throw new EJBException(ex);
		}
	} 
 
	@Override
	public void removeUser(Integer id) {
	try
	{
		Users u = em.find(Users.class, id);
		em.remove(u);
	}
	catch (Exception ex) {
			throw new EJBException(ex);
		}
	} 
 
	@Override
	public UserDetails find(Object pk) {
		Users user = em.find(Users.class, pk);
		return user.convertToUserDetails();
	} 

	@Override
	public List<UserDetails> findAll() {
		return copyUsersToDetails(em.createQuery("select object(o) from Users as o order by o.id").getResultList());
	}
	
	@Override
	public List<UserDetails> findStaff() {
		return copyUsersToDetails(em.createQuery("select object(o) from Users as o where o.roleid.id = 2").getResultList());
	}
	
	/**
	 *
	 * @param username
	 * @return
	 */
	@Override
	public UserDetails findUser(String username) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Users> criteria = builder.createQuery(Users.class);
		Root<Users> from = criteria.from(Users.class);
		criteria.select(from);
		criteria.where(builder.equal(from.get(Users_.username), username));
		TypedQuery<Users> typed = em.createQuery(criteria);
		try {
			return typed.getSingleResult().convertToUserDetails();
		} catch (final NoResultException nre) {
			return null;
		}
	}

	/*
	@Override
	public UserDetails findUser(String username) {
		List<UserDetails> list = copyUsersToDetails(em.createQuery("select object(o) from Users as o where o.username = :username").getResultList());
		if (!list.isEmpty()){
			return list.get(0);
		}
		else{
			return null;
		}
	}
*/
}
