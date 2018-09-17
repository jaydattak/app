package com.example.app.exception;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

public class UserExceptionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testUserException() {
		UserException excp = new UserException("Test Exception");

		assertEquals("Test Exception", excp.getMessage());
	}
	
	@Test
	public final void testUserExceptionWithException() {
		UserException excp = new UserException("Test Exception", new Exception("Exception"));
		assertEquals("Test Exception", excp.getMessage());
		assertEquals(null, excp.getReason());
		
		excp = new UserException("Test Exception", new DataIntegrityViolationException("Exception"));
		assertEquals("Test Exception", excp.getMessage());
		assertEquals("Incorrect Data for save or update", excp.getReason());
		
		excp = new UserException("Test Exception", new DataIntegrityViolationException("Exception"));
		excp.setReason("REASON");
		assertEquals("Test Exception", excp.getMessage());
		assertEquals("REASON", excp.getReason());

	}
	
	

	
}
