package com.example.app.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class BaseServiceTest {

	@InjectMocks
	private BaseService service;

	@Mock
	protected ModelMapper mapper;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testGetMapper() {
		service.setMapper(mapper);
		assertEquals(true, service.getMapper() != null);
	}

}
