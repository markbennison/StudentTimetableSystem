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
import javax.persistence.TypedQuery;
import utils.BookingDetails;

/**
 *
 * @author markb
 */
@Stateless (mappedName="bookingfacade") 
public class BookingFacade implements BookingFacadeRemote {

	@PersistenceContext(unitName = "EJBTimetablingSystemPU")
	private EntityManager em;

	public void persist(Object object) {
		em.persist(object);
	}

	private List<BookingDetails> copyBookingsToDetails(List<Bookings> bookings) {
		List<BookingDetails> bookingList = new ArrayList<>();
		Iterator i = bookings.iterator();
		while (i.hasNext()) {
			Bookings booking = (Bookings) i.next();
			BookingDetails details = booking.convertToBookingDetails();
			
			bookingList.add(details);
		}     
		return bookingList;
	} 
	
	@Override
	public void createBooking(BookingDetails details) {
		try {
			Bookings booking = new Bookings(details);
			em.persist(booking);
		}
		catch (Exception ex) {
			throw new EJBException(ex);
		}
	} 
 
	@Override
	public void editBooking (BookingDetails details) {
		try { 
		 Bookings booking = new Bookings(details);
		 em.merge(booking);
		}
		catch (Exception ex) {
			throw new EJBException(ex);
		}
	} 
 
	@Override
	public void removeBooking(Integer id) {
	try
	{
		Bookings b = em.find(Bookings.class, id);
		em.remove(b);
	}
	catch (Exception ex) {
			throw new EJBException(ex);
		}
	} 
 
	@Override
	public BookingDetails find(Object pk) {
		Bookings booking = em.find(Bookings.class, pk);
		return booking.convertToBookingDetails();
	} 

	@Override
	public List<BookingDetails> findAll() {
		return copyBookingsToDetails(em.createQuery("select object(o) from Bookings as o order by o.id").getResultList());
	}
	
	@Override
	public List<BookingDetails> findByUser(Integer userid) {
		TypedQuery<Bookings> query;
		query = em.createQuery("select object(o) from Bookings as o inner join o.classid c inner join c.classlistCollection cl where cl.classlistPK.userid = :uid order by o.dateandtime", Bookings.class);
		return copyBookingsToDetails(query.setParameter("uid", userid).getResultList());
		
		/*
		SELECT BOOKINGS.CLASSID, BOOKINGS.ROOMID, ROOMS.NAME, DATEANDTIME, DURATION 
		FROM BOOKINGS 
		INNER JOIN CLASS ON BOOKINGS.CLASSID = CLASS.ID 
		INNER JOIN CLASSLIST ON CLASS.ID = CLASSLIST.CLASSID 
		INNER JOIN ROOMS ON BOOKINGS.ROOMID = ROOMS.ID 
		WHERE CLASSLIST.USERID=12;
*/
	}
}
