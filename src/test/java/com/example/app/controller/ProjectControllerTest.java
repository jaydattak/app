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

import com.example.app.dto.ProjectDto;
import com.example.app.dto.ResponseMessage;
import com.example.app.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProjectController.class, secure = false)
public class ProjectControllerTest {

	private static final String BASE_URL = "http://localhost:8080/project/";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProjectService service;

	@Autowired
	private ObjectMapper jsonMapper;

	List<ProjectDto> list = new ArrayList<ProjectDto>();

	List<ProjectDto> mulipleItemsList = new ArrayList<ProjectDto>();

	private ResponseMessage successMesg;

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

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "list")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(list), result.getResponse().getContentAsString());
	}

	@Test
	public final void testGetProjectListWithManyItems() throws Exception {
		Mockito.when(service.getProjectList()).thenReturn(mulipleItemsList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "list")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(mulipleItemsList), result.getResponse().getContentAsString());
	}

	@Test
	public final void testAddProject() throws Exception {
		successMesg = new ResponseMessage(true, "Record saved successfully!");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(BASE_URL + "add")
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonMapper.writeValueAsString(list.get(0)));

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(successMesg), result.getResponse().getContentAsString());
	}

	@Test
	public final void testDeleteProject() throws Exception {
		successMesg = new ResponseMessage(true, "Record deleted successfully!");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(BASE_URL + "1")
				.contentType(MediaType.APPLICATION_JSON_UTF8);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(successMesg), result.getResponse().getContentAsString());
	}

	@Test
	public final void testUpdateProject() throws Exception {

		successMesg = new ResponseMessage(true, "Record updated successfully!");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(BASE_URL + "1")
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonMapper.writeValueAsString(list.get(0)));

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(successMesg), result.getResponse().getContentAsString());
	}

	@Test
	public final void testSearchProject() throws Exception {
		Mockito.when(service.searchProject("ParentProject1")).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "search/ParentProject1")
				.contentType(MediaType.APPLICATION_JSON_UTF8);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(list), result.getResponse().getContentAsString());

		Mockito.when(service.searchProject("ParentProject")).thenReturn(mulipleItemsList);

		requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "search/ParentProject")
				.contentType(MediaType.APPLICATION_JSON_UTF8);

		result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(mulipleItemsList), result.getResponse().getContentAsString());
	}

	@Test
	public final void testSortProjects() throws Exception {
		Mockito.when(service.sortProjects("name")).thenReturn(list);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(BASE_URL + "sort/name")
				.contentType(MediaType.APPLICATION_JSON_UTF8);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(jsonMapper.writeValueAsString(list), result.getResponse().getContentAsString());
	}

}
