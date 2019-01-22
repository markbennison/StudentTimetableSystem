/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimetablingSystem;

import java.util.List;
import javax.ejb.Remote;
import utils.BookingDetails;

/**
 *
 * @author markb
 */
@Remote
public interface BookingFacadeRemote {

	public void createBooking(BookingDetails details);

	public void editBooking(BookingDetails details);

	public void removeBooking(Integer id);
	
	public BookingDetails find(Object pk);
	
	public List<BookingDetails> findAll();

	public List<BookingDetails> findByUser(Integer userid);
	
}
