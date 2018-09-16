package com.example.app.dto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

public class ResponseMessageTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testResponseMessageBooleanString() {
		ResponseMessage res = new ResponseMessage(true, "RESPONSE");
		assertEquals("RESPONSE", res.getMessage());
		assertEquals(true, res.isStatus());

		res = new ResponseMessage(false, "RESPONSE");
		assertEquals("RESPONSE", res.getMessage());
		assertEquals(false, res.isStatus());
		
		res = new ResponseMessage(true, "RESPONSE");
		res.setStatus(false);
		res.setMessage("MESSAGE");
		res.setReason("REASON");
		assertEquals("MESSAGE", res.getMessage());
		assertEquals(false, res.isStatus());
		assertEquals("REASON", res.getReason());
	}

	@Test
	public final void testResponseMessageBooleanStringString() {
		ResponseMessage res = new ResponseMessage(true, "RESPONSE", "REASON");
		assertEquals("RESPONSE", res.getMessage());
		assertEquals(true, res.isStatus());
		assertEquals("REASON", res.getReason());
	}

	@Test
	public final void testResponseMessageBooleanStringException() {
		ResponseMessage res = new ResponseMessage(true, "RESPONSE",
				new DataIntegrityViolationException("Incorrect Data"));
		assertEquals("RESPONSE", res.getMessage());
		assertEquals(true, res.isStatus());
		assertEquals("Incorrect data for save or update", res.getReason());
	}

}
