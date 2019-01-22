/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimetablingSystem;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utils.RoomDetails;

/**
 *
 * @author markb
 */
@Stateless (mappedName="roomfacade") 
public class RoomFacade implements RoomFacadeRemote {

	@PersistenceContext(unitName = "EJBTimetablingSystemPU")
	private EntityManager em;

	public void persist(Object object) {
		em.persist(object);
	}

	private List<RoomDetails> copyRoomsToDetails(List<Rooms> rooms) {
		List<RoomDetails> roomList = new ArrayList<>();
		Iterator i = rooms.iterator();
		while (i.hasNext()) {
			Rooms room = (Rooms) i.next();
			RoomDetails details = new RoomDetails(room.getId(), room.getName(), room.getDescription());
			
			roomList.add(details);
		}     
		return roomList;
	} 
	
	@Override
	public void createRoom(RoomDetails details) {
		try {
			Rooms room = new Rooms(details.getName(), details.getDescription());
			em.persist(room);
		}
		catch (Exception ex) {
			throw new EJBException(ex);
		}
	} 
 
	@Override
	public void editRoom (RoomDetails details) {
		try { 
		 Rooms room = new Rooms(details.getId(), details.getName(), details.getDescription());
		 em.merge(room);
		}
		catch (Exception ex) {
			throw new EJBException(ex);
		}
	} 
 
	@Override
	public void removeRoom(Integer id) {
	try
	{
		Rooms r = em.find(Rooms.class, id);
		em.remove(r);
	}
	catch (Exception ex) {
			throw new EJBException(ex);
		}
	} 
 
	@Override
	public RoomDetails find(Object pk) {
		Rooms room = em.find(Rooms.class, pk);
		return new RoomDetails(room.getId(), room.getName(), room.getDescription());
	} 

	@Override
	public List<RoomDetails> findAll() {
		return copyRoomsToDetails(em.createQuery("select object(o) from Rooms as o order by o.id").getResultList());
	}
	
}
