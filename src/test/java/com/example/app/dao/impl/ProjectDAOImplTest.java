package com.example.app.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.app.dto.ProjectDto;
import com.example.app.entity.Project;
import com.example.app.entity.Task;
import com.example.app.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectDAOImplTest {

	@InjectMocks
	private ProjectDAOImpl dao;

	@Mock
	private ModelMapper mapper;

	@Mock
	private ObjectMapper jsonMapper;

	List<Project> list = new ArrayList<Project>();

	List<Project> mulipleItemsList = new ArrayList<Project>();

	List<ProjectDto> dtoList = new ArrayList<ProjectDto>();

	List<ProjectDto> dtoMulipleItemsList = new ArrayList<ProjectDto>();

	@Mock
	private EntityManager entityManager;

	@Mock
	private Query query;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		populateList();
	}

	private java.util.Date getToday() {
		Calendar calender = Calendar.getInstance();
		calender.set(2018, 5, 6, 0, 0, 0);
		java.util.Date date = calender.getTime();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String str = formatter.format(calender.getTime());
			date = formatter.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	private void populateList() {
		Project project = new Project();
		project.setId(1);
		project.setName("Project1");
		project.setPriority(1);
		project.setStartDate(getToday());
		project.setEndDate(getToday());

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
		project.setName("Project2");

		User user = new User();
		user.setId(1);
		user.setFirstName("First");
		user.setLastName("Last");
		user.setEmployeeId("100001");
		project.setManager(user);

		tasks = new HashSet<Task>();
		tasks.add(task);

		task = new Task();
		task.setId(2);
		task.setName("T2");

		tasks.add(task);
		project.setTasks(tasks);

		mulipleItemsList.add(project);
	}

	@Test
	public final void testGetProjectList() {
		when(entityManager.createQuery("from Project")).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		List<Project> projects = dao.getProjectList();
		assertEquals(1, projects.size());
		assertEquals(1, projects.get(0).getPriority());
		assertEquals(0, projects.get(0).getStartDate().compareTo(getToday()));
		assertEquals(0, projects.get(0).getEndDate().compareTo(getToday()));
	}

	@Test
	public final void testAddProject() {
		Project project = list.get(0);
		User user = new User();
		user.setId(1);
		user.setFirstName("FName");
		user.setEmployeeId("1001");
		project.setManager(user);
		when(entityManager.contains(project.getManager())).thenReturn(true);
		when(entityManager.getReference(User.class, project.getManager().getId())).thenReturn(user);

		dao.addProject(project);

		verify(entityManager, times(1)).persist(project);
	}

	@Test
	public final void testAddProjectWithoutReference() {
		Project project = list.get(0);
		User user = new User();
		user.setId(1);
		user.setFirstName("FName");
		user.setEmployeeId("1001");
		project.setManager(user);

		when(entityManager.contains(project.getManager())).thenReturn(false);
		when(entityManager.getReference(User.class, project.getManager().getId())).thenReturn(user);

		dao.addProject(project);

		verify(entityManager, times(1)).persist(project);

	}

	@Test
	public final void testUpdateProject() {
		Project project = list.get(0);
		when(entityManager.getReference(Project.class, project.getId())).thenReturn(project);
		dao.updateProject(project);

		verify(entityManager, times(1)).merge(project);
	}

	@Test
	public final void testUpdateProjectWithManager() {
		Project project = mulipleItemsList.get(1);
		when(entityManager.getReference(User.class, project.getManager().getId())).thenReturn(project.getManager());
		when(entityManager.getReference(Project.class, project.getId())).thenReturn(project);
		dao.updateProject(project);

		verify(entityManager, times(1)).merge(project);
	}

	@Test
	public final void testDeleteProject() {
		Project project = list.get(0);
		when(entityManager.contains(project)).thenReturn(true);
		when(entityManager.getReference(Project.class, project.getId())).thenReturn(project);

		dao.deleteProject(project);

		verify(entityManager, times(1)).remove(project);
	}

	@Test
	public final void testDeleteProjectWithoutReference() {
		Project project = list.get(0);

		when(entityManager.contains(project)).thenReturn(false);
		when(entityManager.getReference(Project.class, project.getId())).thenReturn(project);

		dao.deleteProject(project);

		verify(entityManager, times(1)).remove(project);
	}

	@Test
	public final void testSearchProjects() {
		when(entityManager.createQuery("from Project where name like ?1")).thenReturn(query);
		when(query.setParameter(1, "%Project1%")).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		List<Project> projects = dao.searchProjects("Project1");
		assertEquals(1, projects.size());
		assertEquals("Project1", projects.get(0).getName());
	}

	@Test
	public final void testSortProjects() {
		when(entityManager.createQuery("from Project order by employeeId")).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		List<Project> projects = dao.sortProjects("id");
		assertEquals(1, projects.size());
	}

	@Test
	public final void testSortProjectsWithSortCompleted() {
		when(entityManager.createQuery("from Project where status = 'completed' order by status")).thenReturn(query);
		when(query.setParameter(1, "completed")).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		List<Project> projects = dao.sortProjects("completed");
		assertEquals(1, projects.size());
	}

	@Test
	public final void testSortProjectstWithSortStartDate() {
		when(entityManager.createQuery("from Project order by startDate")).thenReturn(query);
		when(query.setParameter(1, "startDate")).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		List<Project> projects = dao.sortProjects("startDate");
		assertEquals(1, projects.size());
	}
}
