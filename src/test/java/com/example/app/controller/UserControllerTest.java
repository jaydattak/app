package com.example.app.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.app.dto.UserDto;
import com.example.app.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

	private static final String BASE_URL = "http://localhost:8080/";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService service;

	@MockBean
	private ObjectMapper mapper;

	List<UserDto> list = new ArrayList<UserDto>();

	List<UserDto> mulipleItemsList = new ArrayList<UserDto>();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		UserDto obj = new UserDto();
		obj.setId(1);
		obj.setFirstName("User1");
		obj.setLastName("Last1");
		obj.setEmployeeId("1");
		list.add(obj);

		mulipleItemsList.add(obj);
		obj = new UserDto();
		obj.setId(2);
		obj.setFirstName("User2");
		obj.setLastName("Last2");
		obj.setEmployeeId("2");
		mulipleItemsList.add(obj);

	}

	@Test
	public final void testGetUserList() throws Exception {
		Mockito.when(service.getUserList()).thenReturn(list);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "user/list")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(mapper.writeValueAsString(mulipleItemsList), result.getResponse().getContentAsString());
	}

	@Test
	public final void testGetUserListWithManyItems() throws Exception {
		Mockito.when(service.getUserList()).thenReturn(mulipleItemsList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "user/list")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(mapper.writeValueAsString(mulipleItemsList), result.getResponse().getContentAsString());
	}

	@Test
	public final void testAddUser() throws Exception {
		Mockito.when(service.getUserList()).thenReturn(mulipleItemsList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "user/list")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(mapper.writeValueAsString(mulipleItemsList), result.getResponse().getContentAsString());
	}

	@Test
	public final void testDeleteUser() throws Exception {
		Mockito.when(service.getUserList()).thenReturn(mulipleItemsList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "user/list")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(mapper.writeValueAsString(mulipleItemsList), result.getResponse().getContentAsString());
	}

	@Test
	public final void testUpdateUser() throws Exception {
		Mockito.when(service.getUserList()).thenReturn(mulipleItemsList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "user/list")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(mapper.writeValueAsString(mulipleItemsList), result.getResponse().getContentAsString());
	}

	@Test
	public final void testSearchUser() throws Exception {
		Mockito.when(service.getUserList()).thenReturn(mulipleItemsList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "user/list")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(mapper.writeValueAsString(mulipleItemsList), result.getResponse().getContentAsString());
	}

	@Test
	public final void testSortUsers() throws Exception {
		Mockito.when(service.getUserList()).thenReturn(mulipleItemsList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "user/list")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(mapper.writeValueAsString(mulipleItemsList), result.getResponse().getContentAsString());
	}

	@Test
	public final void testGetAppMessage() throws Exception {
		Mockito.when(service.getUserList()).thenReturn(mulipleItemsList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "user/list")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(mapper.writeValueAsString(mulipleItemsList), result.getResponse().getContentAsString());
	}

}
