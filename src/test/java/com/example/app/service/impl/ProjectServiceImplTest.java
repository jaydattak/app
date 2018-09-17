package com.example.app.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.app.dao.ProjectDAO;
import com.example.app.dto.ProjectDto;
import com.example.app.entity.Project;
import com.example.app.entity.Task;
import com.example.app.exception.UserException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectServiceImplTest {

	@Mock
	private ProjectDAO dao;

	@InjectMocks
	private ProjectServiceImpl service;

	@Mock
	private ModelMapper mapper;

	@Mock
	private ObjectMapper jsonMapper;

	List<Project> list = new ArrayList<Project>();

	List<Project> mulipleItemsList = new ArrayList<Project>();

	List<ProjectDto> dtoList = new ArrayList<ProjectDto>();

	List<ProjectDto> dtoMulipleItemsList = new ArrayList<ProjectDto>();

	@Before
	public void setUp() throws Exception {
		populateList();
		populateDtoList();
	}

	private void populateList() {
		Project project = new Project();
		project.setId(1);
		project.setName("ParentProject1");

		Task task = new Task();
		task.setId(1);
		task.setName("T1");

		Set<Task> tasks = new HashSet<Task>();
		tasks.add(task);
		project.setTasks(tasks);

		list.add(project);

		mulipleItemsList.add(project);

		project = new Project();
		project.setId(2);
		project.setName("ParentProject2");

		tasks = new HashSet<Task>();
		tasks.add(task);

		task = new Task();
		task.setId(2);
		task.setName("T2");

		tasks.add(task);
		project.setTasks(tasks);

		mulipleItemsList.add(project);
	}

	private void populateDtoList() {

		ProjectDto projectDto = new ProjectDto();
		projectDto.setId(1);
		projectDto.setName("ParentProject1");

		dtoList.add(projectDto);

		dtoMulipleItemsList.add(projectDto);
		projectDto = new ProjectDto();
		projectDto.setId(2);
		projectDto.setName("ParentProject2");

		dtoMulipleItemsList.add(projectDto);
	}

	@Test
	public final void testGetProjectList() {
		when(dao.getProjectList()).thenReturn(list);

		Project project = list.get(0);
		ProjectDto projectDto = dtoList.get(0);

		when(mapper.map(project, ProjectDto.class)).thenReturn(projectDto);

		List<ProjectDto> projects = service.getProjectList();

		assertEquals(1, projects.size());
		assertEquals(1, projects.get(0).getId());
		assertEquals("ParentProject1", projects.get(0).getName());
	}

	@Test
	public final void testGetProjectListWithMultipleItems() {
		when(dao.getProjectList()).thenReturn(mulipleItemsList);

		Project project = mulipleItemsList.get(0);
		ProjectDto projectDto = dtoMulipleItemsList.get(0);

		when(mapper.map(project, ProjectDto.class)).thenReturn(projectDto);

		project = mulipleItemsList.get(1);
		projectDto = dtoMulipleItemsList.get(1);

		when(mapper.map(project, ProjectDto.class)).thenReturn(projectDto);

		List<ProjectDto> projects = service.getProjectList();

		assertEquals(2, projects.size());

		assertEquals(1, projects.get(0).getId());
		assertEquals("ParentProject1", projects.get(0).getName());
		assertEquals(1, projects.get(0).getNoOfTasks());

		assertEquals(2, projects.get(1).getId());
		assertEquals("ParentProject2", projects.get(1).getName());
		assertEquals(2, projects.get(1).getNoOfTasks());

	}

	@Test
	public final void testAddProject() throws UserException {
		Project project = new Project();
		project.setId(1);
		project.setName("PR");
		ProjectDto projectDto = new ProjectDto();
		projectDto.setName("PR");
		when(mapper.map(projectDto, Project.class)).thenReturn(project);
		service.addProject(projectDto);
		verify(dao, times(1)).addProject(project);
	}

	@Test(expected = UserException.class)
	public final void testAddProjectWithException() throws UserException {
		Project obj = list.get(0);
		ProjectDto dto = dtoList.get(0);
		doThrow(Exception.class).when(dao).addProject(obj);
		when(mapper.map(dto, Project.class)).thenReturn(obj);
		service.addProject(dto);
	}

	@Test
	public final void testDeleteProject() throws UserException {
		Project project = list.get(0);
		ProjectDto projectDto = dtoList.get(0);
		when(mapper.map(projectDto, Project.class)).thenReturn(project);
		service.deleteProject(1);
		verify(dao, times(1)).deleteProject(project);
	}

	@Test(expected = UserException.class)
	public final void testDeleteProjectWithException() throws UserException {
		Project obj = list.get(0);
		ProjectDto dto = dtoList.get(0);
		doThrow(Exception.class).when(dao).deleteProject(obj);
		when(mapper.map(dto, Project.class)).thenReturn(obj);
		service.deleteProject(dto.getId());
	}

	@Test
	public final void testUpdateProject() throws UserException {
		Project project = list.get(0);
		ProjectDto projectDto = dtoList.get(0);
		when(mapper.map(projectDto, Project.class)).thenReturn(project);
		service.updateProject(projectDto, 1);
		verify(dao, times(1)).updateProject(project);
	}

	@Test(expected = UserException.class)
	public final void testUpdateProjectWithException() throws UserException {
		Project obj = list.get(0);
		ProjectDto dto = dtoList.get(0);
		doThrow(Exception.class).when(dao).updateProject(obj);
		when(mapper.map(dto, Project.class)).thenReturn(obj);
		service.updateProject(dto, dto.getId());
	}

	@Test
	public final void testSearchProject() {
		when(dao.searchProjects("1")).thenReturn(list);
		Project project = list.get(0);
		ProjectDto projectDto = dtoList.get(0);

		when(mapper.map(project, ProjectDto.class)).thenReturn(projectDto);

		List<ProjectDto> projects = service.searchProject("1");

		assertEquals(1, projects.size());
		assertEquals(1, projects.get(0).getId());
		assertEquals("ParentProject1", projects.get(0).getName());
	}

	@Test
	public final void testSortProjects() {
		when(dao.sortProjects("name")).thenReturn(list);
		ProjectDto projectDto = dtoList.get(0);
		Project project = list.get(0);
		when(mapper.map(project, ProjectDto.class)).thenReturn(projectDto);

		List<ProjectDto> projects = service.sortProjects("name");

		assertEquals(1, projects.size());
		assertEquals(1, project.getId());
		assertEquals("ParentProject1", project.getName());

	}

}
