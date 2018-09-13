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

import com.example.app.dto.TaskDto;
import com.example.app.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TaskController.class, secure = false)
public class TaskControllerTest {

	private static final String BASE_URL = "http://localhost:8080/";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ObjectMapper mapper;

	@MockBean
	private TaskService service;

	List<TaskDto> list = new ArrayList<TaskDto>();

	List<TaskDto> mulipleItemsList = new ArrayList<TaskDto>();

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
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "task/list")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(mapper.writeValueAsString(list), result.getResponse().getContentAsString());
	}

	@Test
	public final void testGetTaskListWithManyItems() throws Exception {
		Mockito.when(service.getTaskList()).thenReturn(mulipleItemsList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "task/list")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(mapper.writeValueAsString(mulipleItemsList), result.getResponse().getContentAsString());
	}

	@Test
	public final void testAddTask() {

	}

	@Test
	public final void testDeleteTask() {
	}

	@Test
	public final void testUpdateTask() {
	}

	@Test
	public final void testSearchTask() {
	}

	@Test
	public final void testSortTasks() {
	}

	@Test
	public final void testGetTaskListByProject() {
	}

	@Test
	public final void testGetTaskListByProjectWithSort() {
	}

	@Test
	public final void testGetAppMessage() {
	}

}
