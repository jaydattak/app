package com.example.app.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.example.app.exception.UserException;

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
		UserException ex = new UserException("Incorrect Data");
		ex.setReason("Incorrect Data");
		ResponseMessage res = new ResponseMessage(true, "RESPONSE", ex);
		assertEquals("RESPONSE", res.getMessage());
		assertEquals(true, res.isStatus());
		assertEquals("Incorrect Data", res.getReason());
	}

}
