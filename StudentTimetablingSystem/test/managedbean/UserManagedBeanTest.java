/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.util.ArrayList;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.RoleDetails;
import utils.UserDetails;

/**
 *
 * @author markb
 */
public class UserManagedBeanTest {
	
	public UserManagedBeanTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}

	/**
	 * Test of getList method, of class UserManagedBean.
	 */
	@Test
	public void testGetList() {
		System.out.println("getList");
		UserManagedBean instance = new UserManagedBean();
		ArrayList<UserDetails> expResult = null;
		ArrayList<UserDetails> result = instance.getList();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setList method, of class UserManagedBean.
	 */
	@Test
	public void testSetList() {
		System.out.println("setList");
		ArrayList<UserDetails> list = null;
		UserManagedBean instance = new UserManagedBean();
		instance.setList(list);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getStafflist method, of class UserManagedBean.
	 */
	@Test
	public void testGetStafflist() {
		System.out.println("getStafflist");
		UserManagedBean instance = new UserManagedBean();
		ArrayList<UserDetails> expResult = null;
		ArrayList<UserDetails> result = instance.getStafflist();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setStafflist method, of class UserManagedBean.
	 */
	@Test
	public void testSetStafflist() {
		System.out.println("setStafflist");
		ArrayList<UserDetails> stafflist = null;
		UserManagedBean instance = new UserManagedBean();
		instance.setStafflist(stafflist);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getId method, of class UserManagedBean.
	 */
	@Test
	public void testGetId() {
		System.out.println("getId");
		UserManagedBean instance = new UserManagedBean();
		Integer expResult = null;
		Integer result = instance.getId();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setId method, of class UserManagedBean.
	 */
	@Test
	public void testSetId() {
		System.out.println("setId");
		Integer id = null;
		UserManagedBean instance = new UserManagedBean();
		instance.setId(id);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getUsername method, of class UserManagedBean.
	 */
	@Test
	public void testGetUsername() {
		System.out.println("getUsername");
		UserManagedBean instance = new UserManagedBean();
		String expResult = "";
		String result = instance.getUsername();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setUsername method, of class UserManagedBean.
	 */
	@Test
	public void testSetUsername() {
		System.out.println("setUsername");
		String username = "";
		UserManagedBean instance = new UserManagedBean();
		instance.setUsername(username);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getPassword method, of class UserManagedBean.
	 */
	@Test
	public void testGetPassword() {
		System.out.println("getPassword");
		UserManagedBean instance = new UserManagedBean();
		String expResult = "";
		String result = instance.getPassword();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setPassword method, of class UserManagedBean.
	 */
	@Test
	public void testSetPassword() {
		System.out.println("setPassword");
		String passwordplain = "";
		UserManagedBean instance = new UserManagedBean();
		instance.setPassword(passwordplain);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getForename method, of class UserManagedBean.
	 */
	@Test
	public void testGetForename() {
		System.out.println("getForename");
		UserManagedBean instance = new UserManagedBean();
		String expResult = "";
		String result = instance.getForename();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setForename method, of class UserManagedBean.
	 */
	@Test
	public void testSetForename() {
		System.out.println("setForename");
		String forename = "";
		UserManagedBean instance = new UserManagedBean();
		instance.setForename(forename);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getSurname method, of class UserManagedBean.
	 */
	@Test
	public void testGetSurname() {
		System.out.println("getSurname");
		UserManagedBean instance = new UserManagedBean();
		String expResult = "";
		String result = instance.getSurname();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setSurname method, of class UserManagedBean.
	 */
	@Test
	public void testSetSurname() {
		System.out.println("setSurname");
		String surname = "";
		UserManagedBean instance = new UserManagedBean();
		instance.setSurname(surname);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getRoleid method, of class UserManagedBean.
	 */
	@Test
	public void testGetRoleid() {
		System.out.println("getRoleid");
		UserManagedBean instance = new UserManagedBean();
		RoleDetails expResult = null;
		RoleDetails result = instance.getRoleid();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setRoleid method, of class UserManagedBean.
	 */
	@Test
	public void testSetRoleid_RoleDetails() {
		System.out.println("setRoleid");
		RoleDetails roleid = null;
		UserManagedBean instance = new UserManagedBean();
		instance.setRoleid(roleid);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setRoleid method, of class UserManagedBean.
	 */
	@Test
	public void testSetRoleid_Integer() {
		System.out.println("setRoleid");
		Integer roleidvalue = null;
		UserManagedBean instance = new UserManagedBean();
		instance.setRoleid(roleidvalue);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getRoleidvalue method, of class UserManagedBean.
	 */
	@Test
	public void testGetRoleidvalue() {
		System.out.println("getRoleidvalue");
		UserManagedBean instance = new UserManagedBean();
		Integer expResult = null;
		Integer result = instance.getRoleidvalue();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setRoleidvalue method, of class UserManagedBean.
	 */
	@Test
	public void testSetRoleidvalue() {
		System.out.println("setRoleidvalue");
		Integer roleidvalue = null;
		UserManagedBean instance = new UserManagedBean();
		instance.setRoleidvalue(roleidvalue);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getPasswordplain method, of class UserManagedBean.
	 */
	@Test
	public void testGetPasswordplain() {
		System.out.println("getPasswordplain");
		UserManagedBean instance = new UserManagedBean();
		String expResult = "";
		String result = instance.getPasswordplain();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setPasswordplain method, of class UserManagedBean.
	 */
	@Test
	public void testSetPasswordplain() {
		System.out.println("setPasswordplain");
		String passwordplain = "";
		UserManagedBean instance = new UserManagedBean();
		instance.setPasswordplain(passwordplain);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getPasswordcheck method, of class UserManagedBean.
	 */
	@Test
	public void testGetPasswordcheck() {
		System.out.println("getPasswordcheck");
		UserManagedBean instance = new UserManagedBean();
		String expResult = "";
		String result = instance.getPasswordcheck();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setPasswordcheck method, of class UserManagedBean.
	 */
	@Test
	public void testSetPasswordcheck() {
		System.out.println("setPasswordcheck");
		String passwordcheck = "";
		UserManagedBean instance = new UserManagedBean();
		instance.setPasswordcheck(passwordcheck);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getMessage method, of class UserManagedBean.
	 */
	@Test
	public void testGetMessage() {
		System.out.println("getMessage");
		UserManagedBean instance = new UserManagedBean();
		String expResult = "";
		String result = instance.getMessage();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setMessage method, of class UserManagedBean.
	 */
	@Test
	public void testSetMessage() {
		System.out.println("setMessage");
		String message = "";
		UserManagedBean instance = new UserManagedBean();
		instance.setMessage(message);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getDeletedrecord method, of class UserManagedBean.
	 */
	@Test
	public void testGetDeletedrecord() {
		System.out.println("getDeletedrecord");
		UserManagedBean instance = new UserManagedBean();
		Integer expResult = null;
		Integer result = instance.getDeletedrecord();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setDeletedrecord method, of class UserManagedBean.
	 */
	@Test
	public void testSetDeletedrecord() {
		System.out.println("setDeletedrecord");
		Integer deletedrecord = null;
		UserManagedBean instance = new UserManagedBean();
		instance.setDeletedrecord(deletedrecord);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of resetFields method, of class UserManagedBean.
	 */
	@Test
	public void testResetFields() {
		System.out.println("resetFields");
		UserManagedBean instance = new UserManagedBean();
		instance.resetFields();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of createRecord method, of class UserManagedBean.
	 */
	@Test
	public void testCreateRecord() {
		System.out.println("createRecord");
		UserManagedBean instance = new UserManagedBean();
		String expResult = "";
		String result = instance.createRecord();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getRecord method, of class UserManagedBean.
	 */
	@Test
	public void testGetRecord_0args() {
		System.out.println("getRecord");
		UserManagedBean instance = new UserManagedBean();
		String expResult = "";
		String result = instance.getRecord();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getRecord method, of class UserManagedBean.
	 */
	@Test
	public void testGetRecord_Integer() {
		System.out.println("getRecord");
		Integer requestedId = null;
		UserManagedBean instance = new UserManagedBean();
		String expResult = "";
		String result = instance.getRecord(requestedId);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of editRecord method, of class UserManagedBean.
	 */
	@Test
	public void testEditRecord() {
		System.out.println("editRecord");
		UserManagedBean instance = new UserManagedBean();
		String expResult = "";
		String result = instance.editRecord();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of deleteRecord method, of class UserManagedBean.
	 */
	@Test
	public void testDeleteRecord_0args() {
		System.out.println("deleteRecord");
		UserManagedBean instance = new UserManagedBean();
		String expResult = "";
		String result = instance.deleteRecord();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of deleteRecord method, of class UserManagedBean.
	 */
	@Test
	public void testDeleteRecord_Integer() {
		System.out.println("deleteRecord");
		Integer requestedId = null;
		UserManagedBean instance = new UserManagedBean();
		String expResult = "";
		String result = instance.deleteRecord(requestedId);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of validateUsername method, of class UserManagedBean.
	 */
	@Test
	public void testValidateUsername() {
		System.out.println("validateUsername");
		FacesContext context = null;
		UIComponent component = null;
		Object value = null;
		UserManagedBean instance = new UserManagedBean();
		instance.validateUsername(context, component, value);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of validatePasswordPlain method, of class UserManagedBean.
	 */
	@Test
	public void testValidatePasswordPlain() {
		System.out.println("validatePasswordPlain");
		FacesContext context = null;
		UIComponent component = null;
		Object value = null;
		UserManagedBean instance = new UserManagedBean();
		instance.validatePasswordPlain(context, component, value);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of validatePasswordCheck method, of class UserManagedBean.
	 */
	@Test
	public void testValidatePasswordCheck() {
		System.out.println("validatePasswordCheck");
		ComponentSystemEvent event = null;
		UserManagedBean instance = new UserManagedBean();
		instance.validatePasswordCheck(event);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of validateForename method, of class UserManagedBean.
	 */
	@Test
	public void testValidateForename() {
		System.out.println("validateForename");
		FacesContext context = null;
		UIComponent component = null;
		Object value = null;
		UserManagedBean instance = new UserManagedBean();
		instance.validateForename(context, component, value);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of validateSurname method, of class UserManagedBean.
	 */
	@Test
	public void testValidateSurname() {
		System.out.println("validateSurname");
		FacesContext context = null;
		UIComponent component = null;
		Object value = null;
		UserManagedBean instance = new UserManagedBean();
		instance.validateSurname(context, component, value);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
	
}
