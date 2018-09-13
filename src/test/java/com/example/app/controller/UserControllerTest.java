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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.app.dto.ResponseMessage;
import com.example.app.dto.UserDto;
import com.example.app.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

	private static final String BASE_URL = "http://localhost:8080/user/";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService service;

	@Autowired
	private ObjectMapper jsonMapper;

	List<UserDto> list = new ArrayList<UserDto>();

	List<UserDto> mulipleItemsList = new ArrayList<UserDto>();

	private ResponseMessage successMesg;

	private ResponseMessage errorMesg;

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
		Mockito.when(service.getUserList()).thenReturn(mulipleItemsList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "list")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(jsonMapper.writeValueAsString(mulipleItemsList), result.getResponse().getContentAsString());
	}

	@Test
	public final void testGetUserListWithManyItems() throws Exception {
		Mockito.when(service.getUserList()).thenReturn(mulipleItemsList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "list")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(jsonMapper.writeValueAsString(mulipleItemsList), result.getResponse().getContentAsString());
	}

	@Test
	public final void testAddUser() throws Exception {
		successMesg = new ResponseMessage(true, "Record saved successfully!");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(BASE_URL + "add")
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonMapper.writeValueAsString(list.get(0)));

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(successMesg), result.getResponse().getContentAsString());
	}

	@Test
	public final void testDeleteUser() throws Exception {
		successMesg = new ResponseMessage(true, "Record deleted successfully!");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(BASE_URL + "1")
				.contentType(MediaType.APPLICATION_JSON_UTF8);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(successMesg), result.getResponse().getContentAsString());
	}

	@Test
	public final void testUpdateUser() throws Exception {
		successMesg = new ResponseMessage(true, "Record updated successfully!");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(BASE_URL + "1")
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonMapper.writeValueAsString(list.get(0)));

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(successMesg), result.getResponse().getContentAsString());
	}

	@Test
	public final void testSearchUser() throws Exception {
		Mockito.when(service.searchUser("User1")).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "search/User1")
				.contentType(MediaType.APPLICATION_JSON_UTF8);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(list), result.getResponse().getContentAsString());
	}

	@Test
	public final void testSortUsers() throws Exception {
		Mockito.when(service.sortUsers("firstName")).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "sort/firstName")
				.contentType(MediaType.APPLICATION_JSON_UTF8);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(list), result.getResponse().getContentAsString());
	}

}
