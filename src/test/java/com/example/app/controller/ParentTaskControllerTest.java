/**
 * 
 */
package com.example.app.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

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

import com.example.app.dto.ParentTaskDto;
import com.example.app.dto.ResponseMessage;
import com.example.app.exception.UserException;
import com.example.app.service.impl.ParentTaskServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Admin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = ParentTaskController.class, secure = false)
public class ParentTaskControllerTest {

	private static final String BASE_URL = "http://localhost:8080/parenttask/";

	@Autowired
	private ObjectMapper jsonMapper;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ParentTaskServiceImpl service;

	List<ParentTaskDto> list = new ArrayList<ParentTaskDto>();

	List<ParentTaskDto> mulipleItemsList = new ArrayList<ParentTaskDto>();

	private ResponseMessage successMesg;

	private ResponseMessage errorMesg;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ParentTaskDto obj = new ParentTaskDto();
		obj.setId(1);
		obj.setName("ParentTask1");
		list.add(obj);

		mulipleItemsList.add(obj);
		obj = new ParentTaskDto();
		obj.setId(2);
		obj.setName("ParentTask2");
		mulipleItemsList.add(obj);

	}

	/**
	 * Test method for
	 * {@link com.example.app.controller.ParentTaskController#getTaskList()}.
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testGetTaskList() throws Exception {
		when(service.getTaskList()).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "list")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(list), result.getResponse().getContentAsString());
	}

	@Test
	public final void testGetTaskListWithManyItems() throws Exception {
		when(service.getTaskList()).thenReturn(mulipleItemsList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "list")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(mulipleItemsList), result.getResponse().getContentAsString());
	}

	/**
	 * Test method for
	 * {@link com.example.app.controller.ParentTaskController#addTask(com.example.app.dto.ParentTaskDto)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testAddTask() throws Exception {
		successMesg = new ResponseMessage(true, "Record saved successfully!");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(BASE_URL + "add")
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonMapper.writeValueAsString(list.get(0)));

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(successMesg), result.getResponse().getContentAsString());
	}

	@Test
	public final void testAddTaskWithException() throws Exception {
		errorMesg = new ResponseMessage(false, "Record not saved!");

		ParentTaskDto dto = list.get(0);

		doThrow(new UserException("Exception")).when(service).addTask(dto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(BASE_URL + "add")
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonMapper.writeValueAsString(list.get(0)));

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(errorMesg), result.getResponse().getContentAsString());
	}

	@Test
	public final void testSearchTask() throws Exception {

		Mockito.when(service.searchTask("ParentTask")).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "search/ParentTask")
				.contentType(MediaType.APPLICATION_JSON_UTF8);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(list), result.getResponse().getContentAsString());

		Mockito.when(service.searchTask("ParentTask")).thenReturn(mulipleItemsList);

		requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "search/ParentTask")
				.contentType(MediaType.APPLICATION_JSON_UTF8);

		result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(mulipleItemsList), result.getResponse().getContentAsString());
	}

}
