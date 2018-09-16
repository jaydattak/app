package com.example.app.exception;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserExceptionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testUserException() {
		UserException excp = new UserException("Test Exception");

		assertEquals("Test Exception", excp.getMessage());
	}

	
}
