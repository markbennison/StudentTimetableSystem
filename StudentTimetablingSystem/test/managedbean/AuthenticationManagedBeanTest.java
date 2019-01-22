/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.RoleDetails;

/**
 *
 * @author markb
 */
public class AuthenticationManagedBeanTest {
	
	public AuthenticationManagedBeanTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}

	/**
	 * Test of getId method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testGetId() {
		System.out.println("getId");
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		Integer expResult = null;
		Integer result = instance.getId();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setId method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testSetId() {
		System.out.println("setId");
		Integer id = null;
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		instance.setId(id);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getUsername method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testGetUsername() {
		System.out.println("getUsername");
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		String expResult = "";
		String result = instance.getUsername();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setUsername method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testSetUsername() {
		System.out.println("setUsername");
		String username = "";
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		instance.setUsername(username);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getPassword method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testGetPassword() {
		System.out.println("getPassword");
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		String expResult = "";
		String result = instance.getPassword();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setPassword method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testSetPassword() {
		System.out.println("setPassword");
		String password = "";
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		instance.setPassword(password);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getForename method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testGetForename() {
		System.out.println("getForename");
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		String expResult = "";
		String result = instance.getForename();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setForename method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testSetForename() {
		System.out.println("setForename");
		String forename = "";
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		instance.setForename(forename);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getSurname method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testGetSurname() {
		System.out.println("getSurname");
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		String expResult = "";
		String result = instance.getSurname();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setSurname method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testSetSurname() {
		System.out.println("setSurname");
		String surname = "";
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		instance.setSurname(surname);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getRoleid method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testGetRoleid() {
		System.out.println("getRoleid");
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		RoleDetails expResult = null;
		RoleDetails result = instance.getRoleid();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setRoleid method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testSetRoleid() {
		System.out.println("setRoleid");
		RoleDetails roleid = null;
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		instance.setRoleid(roleid);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getRoleidvalue method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testGetRoleidvalue() {
		System.out.println("getRoleidvalue");
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		Integer expResult = null;
		Integer result = instance.getRoleidvalue();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setRoleidvalue method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testSetRoleidvalue() {
		System.out.println("setRoleidvalue");
		Integer roleidvalue = null;
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		instance.setRoleidvalue(roleidvalue);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getUsernameattempt method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testGetUsernameattempt() {
		System.out.println("getUsernameattempt");
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		String expResult = "";
		String result = instance.getUsernameattempt();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setUsernameattempt method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testSetUsernameattempt() {
		System.out.println("setUsernameattempt");
		String usernameattempt = "";
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		instance.setUsernameattempt(usernameattempt);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getPasswordattempt method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testGetPasswordattempt() {
		System.out.println("getPasswordattempt");
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		String expResult = "";
		String result = instance.getPasswordattempt();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setPasswordattempt method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testSetPasswordattempt() {
		System.out.println("setPasswordattempt");
		String passwordattempt = "";
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		instance.setPasswordattempt(passwordattempt);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of isCredentialsOK method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testIsCredentialsOK() {
		System.out.println("isCredentialsOK");
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		boolean expResult = false;
		boolean result = instance.isCredentialsOK();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setCredentialsOK method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testSetCredentialsOK() {
		System.out.println("setCredentialsOK");
		boolean credentialsOK = false;
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		instance.setCredentialsOK(credentialsOK);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getMessage method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testGetMessage() {
		System.out.println("getMessage");
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		String expResult = "";
		String result = instance.getMessage();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setMessage method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testSetMessage() {
		System.out.println("setMessage");
		String message = "";
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		instance.setMessage(message);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of resetFields method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testResetFields() {
		System.out.println("resetFields");
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		instance.resetFields();
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of signIn method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testSignIn() {
		System.out.println("signIn");
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		String expResult = "";
		String result = instance.signIn();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of signOut method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testSignOut() {
		System.out.println("signOut");
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		String expResult = "";
		String result = instance.signOut();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of validateUsername method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testValidateUsername() {
		System.out.println("validateUsername");
		FacesContext context = null;
		UIComponent component = null;
		Object value = null;
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		instance.validateUsername(context, component, value);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of validatePassword method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testValidatePassword() {
		System.out.println("validatePassword");
		FacesContext context = null;
		UIComponent component = null;
		Object value = null;
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		instance.validatePassword(context, component, value);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getLoginControlValue method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testGetLoginControlValue() {
		System.out.println("getLoginControlValue");
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		String expResult = "";
		String result = instance.getLoginControlValue();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getLoginControlAction method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testGetLoginControlAction() {
		System.out.println("getLoginControlAction");
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		String expResult = "";
		String result = instance.getLoginControlAction();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getUserMenuVisibility method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testGetUserMenuVisibility() {
		System.out.println("getUserMenuVisibility");
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		String expResult = "";
		String result = instance.getUserMenuVisibility();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getStaffMenuVisibility method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testGetStaffMenuVisibility() {
		System.out.println("getStaffMenuVisibility");
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		String expResult = "";
		String result = instance.getStaffMenuVisibility();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getAdminMenuVisibility method, of class AuthenticationManagedBean.
	 */
	@Test
	public void testGetAdminMenuVisibility() {
		System.out.println("getAdminMenuVisibility");
		AuthenticationManagedBean instance = new AuthenticationManagedBean();
		String expResult = "";
		String result = instance.getAdminMenuVisibility();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}
	
}
