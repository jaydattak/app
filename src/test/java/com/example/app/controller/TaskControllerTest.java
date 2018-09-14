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

import com.example.app.dto.ResponseMessage;
import com.example.app.dto.TaskDto;
import com.example.app.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TaskController.class, secure = false)
public class TaskControllerTest {

	private static final String BASE_URL = "http://localhost:8080/task/";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper jsonMapper;

	@MockBean
	private TaskService service;

	List<TaskDto> list = new ArrayList<TaskDto>();

	List<TaskDto> mulipleItemsList = new ArrayList<TaskDto>();

	private ResponseMessage successMesg;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TaskDto obj = new TaskDto();
		obj.setId(1);
		obj.setName("Task1");
		list.add(obj);

		mulipleItemsList.add(obj);
		obj = new TaskDto();
		obj.setId(2);
		obj.setName("Task2");
		mulipleItemsList.add(obj);

	}

	@Test
	public final void testGetTaskList() throws Exception {
		Mockito.when(service.getTaskList()).thenReturn(list);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "list")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(jsonMapper.writeValueAsString(list), result.getResponse().getContentAsString());
	}

	@Test
	public final void testGetTaskListWithManyItems() throws Exception {
		Mockito.when(service.getTaskList()).thenReturn(mulipleItemsList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "list")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(jsonMapper.writeValueAsString(mulipleItemsList), result.getResponse().getContentAsString());
	}

	@Test
	public final void testAddTask() throws Exception {
		successMesg = new ResponseMessage(true, "Record saved successfully!");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(BASE_URL + "add")
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonMapper.writeValueAsString(list.get(0)));

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(successMesg), result.getResponse().getContentAsString());

	}

	@Test
	public final void testDeleteTask() throws Exception {
		successMesg = new ResponseMessage(true, "Record deleted successfully!");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(BASE_URL + "1")
				.contentType(MediaType.APPLICATION_JSON_UTF8);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(successMesg), result.getResponse().getContentAsString());
	}

	@Test
	public final void testUpdateTask() throws Exception {
		successMesg = new ResponseMessage(true, "Record updated successfully!");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(BASE_URL + "1")
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonMapper.writeValueAsString(list.get(0)));

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(successMesg), result.getResponse().getContentAsString());
	}

	@Test
	public final void testSearchTask() throws Exception {
		Mockito.when(service.searchTask("ParentProject1")).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "search/ParentProject1")
				.contentType(MediaType.APPLICATION_JSON_UTF8);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(list), result.getResponse().getContentAsString());

		Mockito.when(service.searchTask("ParentProject")).thenReturn(mulipleItemsList);

		requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "search/ParentProject")
				.contentType(MediaType.APPLICATION_JSON_UTF8);

		result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(mulipleItemsList), result.getResponse().getContentAsString());
	}

	@Test
	public final void testSortTasks() throws Exception {
		Mockito.when(service.sortTasks("name")).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "sort/name")
				.contentType(MediaType.APPLICATION_JSON_UTF8);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(list), result.getResponse().getContentAsString());
	}

	@Test
	public final void testGetTaskListByProject() throws Exception {

		Mockito.when(service.getTaskListByProject(1)).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "list/project/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(list), result.getResponse().getContentAsString());
	}

	@Test
	public final void testGetTaskListByProjectWithSort() throws Exception {

		Mockito.when(service.getTaskListByProjectWithSort(1, "name")).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "sort/1/name")
				.contentType(MediaType.APPLICATION_JSON_UTF8);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(list), result.getResponse().getContentAsString());
	}

}
