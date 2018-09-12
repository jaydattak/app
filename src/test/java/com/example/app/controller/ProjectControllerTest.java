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

import com.example.app.dto.ProjectDto;
import com.example.app.service.ProjectService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProjectController.class, secure = false)
public class ProjectControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProjectService service;

	List<ProjectDto> list = new ArrayList<ProjectDto>();

	List<ProjectDto> mulipleItemsList = new ArrayList<ProjectDto>();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ProjectDto obj = new ProjectDto();
		obj.setId(1);
		obj.setName("ParentProject1");
		list.add(obj);

		mulipleItemsList.add(obj);
		obj = new ProjectDto();
		obj.setId(2);
		obj.setName("ParentProject2");
		mulipleItemsList.add(obj);

	}

	@Test
	public final void testGetProjectList() throws Exception {
		Mockito.when(service.getProjectList()).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("http://localhost:8080/project/list")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "[{\"id\":1,\"name\":\"ParentProject1\"}]";

		assertEquals(expected, result.getResponse().getContentAsString());
	}

	@Test
	public final void testGetProjectListWithManyItems() throws Exception {
		Mockito.when(service.getProjectList()).thenReturn(mulipleItemsList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("http://localhost:8080/project/list")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "[{\"id\":1,\"name\":\"ParentProject1\"},{\"id\":2,\"name\":\"ParentProject2\"}]";

		assertEquals(expected, result.getResponse().getContentAsString());
	}

	@Test
	public final void testAddProject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testDeleteProject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testUpdateProject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSearchProject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSortProjects() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAppMessage() {
		fail("Not yet implemented"); // TODO
	}

}
