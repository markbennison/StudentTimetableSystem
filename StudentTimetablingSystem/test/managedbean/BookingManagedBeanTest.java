/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.util.ArrayList;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.BookingDetails;
import utils.ClassDetails;
import utils.RoomDetails;

/**
 *
 * @author markb
 */
public class BookingManagedBeanTest {
	
	public BookingManagedBeanTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}

	@Test(expected=NullPointerException.class)
	public void test_UT1()
	{
		BookingManagedBean booking = new BookingManagedBean();
		Date datetime = new Date(2019-1900,0,18,14,0);
		
		booking.setClassidvalue(3);
		booking.setRoomidvalue(7);
		
		//booking.setDateandtime(datetime);
		//booking.setStarttime(new Date(2019-1900,0,18,14,0));
		//booking.setEndtime(new Date(2019-1900,0,18,15,0));
		
		booking.setBookingdate(new Date(2019-1900,0,18));
		booking.setStarttime(new Date(1970-1900,0,1,14,0));
		booking.setEndtime(new Date(1970-1900,0,1,15,0));
		
		assertNotNull("Booking should exist, but booking is null", booking);
		assertEquals("Class ID should be 3 - ", "3", booking.getClassid().getId().toString());
		assertEquals("Room ID should be 7 - ", "7", booking.getRoomid().getId().toString());
		assertEquals("Dateandtime should be " + datetime.toString() + " - ", datetime.toString(), booking.getDateandtime().toString());
		assertEquals("Duration should be 60 - ", 60, booking.getDuration());
		assertEquals("output should be 'Record successfully added' - ", "Record successfully added", booking.createRecord());
	}
	
	@Test (expected=NullPointerException.class)
	public void test_UT2()
	{
		BookingManagedBean booking = new BookingManagedBean();
		booking.setClassidvalue(null);
		booking.setRoomidvalue(-5);
		booking.setBookingdate(null);
		booking.setStarttime(null);
		booking.setEndtime(null);

		assertNotNull("Booking should exist, but booking is null", booking);
		assertEquals("Class ID should be null - ", null, booking.getClassid().getId().toString());
		assertEquals("Room ID should be -5 - ", "-5", booking.getRoomid().getId().toString());
		assertEquals("Dateandtime should be null - ", null, booking.getDateandtime().toString());
		assertEquals("Duration should be 0 - ", 0, booking.getDuration());
		assertEquals("output should be 'Record does not exist' - ", "Record does not exist", booking.createRecord());
	}
	
	@Test (expected=NullPointerException.class)
	public void test_UT3()
	{
		Date datetime = new Date(1-1900,0,0,0,0);
		
		BookingManagedBean booking = new BookingManagedBean();
		booking.setClassidvalue(0);
		booking.setRoomidvalue(99);
		booking.setBookingdate(new Date(2019-1900,0,1));
		booking.setStarttime(new Date(1970-1900,0,1,0,0));
		booking.setEndtime(new Date(1970-1900,0,1,0,0));

		assertNotNull("Booking should exist, but booking is null", booking);
		assertEquals("Class ID should be null - ", null, booking.getClassid().getId().toString());
		assertEquals("Room ID should be null - ", null, booking.getRoomid().getId().toString());
		assertEquals("Dateandtime should be 1/1/0001 - ", "01/01/0001", booking.getDateandtime().toString());
		assertEquals("Duration should be 0 - ", 0, booking.getDuration());
		assertEquals("output should be 'Record does not exist' - ", "Record does not exist", booking.createRecord());
	}
	
	@Test (expected=NullPointerException.class)
	public void test_UT4()
	{
		BookingManagedBean booking = new BookingManagedBean();
		Date datetime = new Date(2019-1900,0,14,15,0);
		
		booking.setId(24);
		booking.setClassidvalue(5);
		booking.setRoomidvalue(5);
		
		booking.setBookingdate(new Date(2019-1900,0,14));
		booking.setStarttime(new Date(1970-1900,0,1,15,0));
		booking.setEndtime(new Date(1970-1900,0,1,16,30));
		
		assertNotNull("Booking should exist, but booking is null", booking);
		assertEquals("Class ID should be 5 - ", "5", booking.getClassid().getId().toString());
		assertEquals("Room ID should be 5 - ", "5", booking.getRoomid().getId().toString());
		
		assertEquals("output should be 'Record successfully edited' - ", "Record successfully edited", booking.editRecord());
		assertEquals("Dateandtime should be " + datetime.toString() + " - ", datetime.toString(), booking.getDateandtime().toString());
		assertEquals("Duration should be 90 - ", 90, booking.getDuration());
	}
	
	@Test (expected=NullPointerException.class)
	public void test_UT5()
	{
		BookingManagedBean booking = new BookingManagedBean();
		Date datetime = new Date(2019-1900,0,14,15,0);
		
		booking.setId(24);
		booking.setClassidvalue(-99);
		booking.setRoomidvalue(-99);
		booking.setBookingdate(null);
		booking.setStarttime(null);
		booking.setEndtime(null);
		
		assertNotNull("Booking should exist", booking);
		assertEquals("Class ID should be null - ", null, booking.getClassid().getId().toString());
		assertEquals("Room ID should be null - ", null, booking.getRoomid().getId().toString());
		assertEquals("output should be 'Record could not be edited' - ", "Record could not be edited", booking.editRecord());
		assertEquals("Dateandtime should be 1/1/0001 - ", "01/01/0001", booking.getDateandtime().toString());
		assertEquals("Duration should be 0 - ", 0, booking.getDuration());
	}

	@Test (expected=NullPointerException.class)
	public void test_UT6()
	{
		BookingManagedBean booking = new BookingManagedBean();
		Date datetime = new Date(2019-1900,0,14,15,0);
		
		booking.setId(24);

		assertNotNull("Booking should exist", booking);
		assertEquals("output should be 'Record could not be deleted' - ", "Record could not be deleted", booking.deleteRecord(booking.getId()));
	
	}
	
}
