/**
 * 
 */
package com.example.app.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

import com.example.app.controller.ParentTaskController;
import com.example.app.dto.ParentTaskDto;
import com.example.app.service.ParentTaskService;

/**
 * @author Admin
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = ParentTaskController.class, secure = false)
public class ParentTaskControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ParentTaskService service;

	List<ParentTaskDto> list = new ArrayList<ParentTaskDto>();

	List<ParentTaskDto> mulipleItemsList = new ArrayList<ParentTaskDto>();

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
		Mockito.when(service.getTaskList()).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("http://localhost:8080/parent/task/list")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "[{\"id\":1,\"name\":\"ParentTask1\"}]";

		assertEquals(expected, result.getResponse().getContentAsString());
	}

	@Test
	public final void testGetTaskListWithManyItems() throws Exception {
		Mockito.when(service.getTaskList()).thenReturn(mulipleItemsList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("http://localhost:8080/parent/task/list")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "[{\"id\":1,\"name\":\"ParentTask1\"},{\"id\":2,\"name\":\"ParentTask2\"}]";

		assertEquals(expected, result.getResponse().getContentAsString());
	}

	/**
	 * Test method for
	 * {@link com.example.app.controller.ParentTaskController#addTask(com.example.app.dto.ParentTaskDto)}.
	 */
	@Test
	public final void testAddTask() {
		fail("Not yet implemented"); // TODO
	}

}
