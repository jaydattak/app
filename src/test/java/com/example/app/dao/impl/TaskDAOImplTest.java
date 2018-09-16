package com.example.app.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.app.dto.TaskDto;
import com.example.app.entity.ParentTask;
import com.example.app.entity.Project;
import com.example.app.entity.Task;
import com.example.app.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
public class TaskDAOImplTest {

	@InjectMocks
	private TaskDAOImpl dao;

	@Mock
	private ModelMapper mapper;

	@Mock
	private ObjectMapper jsonMapper;

	List<Task> list = new ArrayList<Task>();

	List<Task> mulipleItemsList = new ArrayList<Task>();

	List<TaskDto> dtoList = new ArrayList<TaskDto>();

	List<TaskDto> dtoMulipleItemsList = new ArrayList<TaskDto>();

	@Mock
	private EntityManager entityManager;

	@Mock
	private Query query;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		populateList();
	}

	private void populateList() {
		Task task = new Task();
		task.setId(1);
		task.setName("Task1");
		task.setPriority(15);
		task.setStartDate(getToday());
		task.setEndDate(getToday());
		task.setStatus("STATUS");

		Project project = new Project();
		project.setId(1);
		project.setName("Project1");
		task.setProject(project);

		User user = new User();
		user.setId(1);
		user.setFirstName("FName");
		user.setEmployeeId("1001");
		task.setUser(user);

		ParentTask parentTask = new ParentTask();
		parentTask.setId(1);
		parentTask.setName("ParentTask");
		task.setParentTask(parentTask);

		list.add(task);

		mulipleItemsList.add(task);

		task = new Task();
		task.setId(2);
		task.setName("Task2");

		mulipleItemsList.add(task);
	}

	private java.util.Date getToday() {
		Calendar calender = Calendar.getInstance();
		calender.set(2018, 5, 6, 0, 0, 0);
		return calender.getTime();
	}

	@Test
	public final void testGetTaskList() {
		when(entityManager.createQuery("from Task")).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		List<Task> tasks = dao.getTaskList();
		assertEquals(1, tasks.size());
		assertEquals(15, tasks.get(0).getPriority());
		assertEquals(0, tasks.get(0).getStartDate().compareTo(getToday()));
		assertEquals(0, tasks.get(0).getEndDate().compareTo(getToday()));
		assertEquals("STATUS", tasks.get(0).getStatus());
	}

	@Test
	public final void testAddTask() {
		Task task = list.get(0);

		when(entityManager.contains(task.getProject())).thenReturn(true);
		when(entityManager.getReference(Project.class, task.getProject().getId())).thenReturn(task.getProject());

		when(entityManager.contains(task.getUser())).thenReturn(true);
		when(entityManager.getReference(User.class, task.getUser().getId())).thenReturn(task.getUser());

		dao.addTask(task);

		verify(entityManager, times(1)).persist(task);
	}

	@Test
	public final void testUpdateTask() {
		Task task = list.get(0);

		when(entityManager.contains(task.getProject())).thenReturn(true);
		when(entityManager.getReference(Project.class, task.getProject().getId())).thenReturn(task.getProject());

		when(entityManager.contains(task.getUser())).thenReturn(true);
		when(entityManager.getReference(User.class, task.getUser().getId())).thenReturn(task.getUser());

		when(entityManager.contains(task.getParentTask())).thenReturn(true);
		when(entityManager.getReference(ParentTask.class, task.getParentTask().getId()))
				.thenReturn(task.getParentTask());

		dao.updateTask(task);

		verify(entityManager, times(1)).merge(task);
	}

	@Test
	public final void testDeleteTask() {
		Task task = list.get(0);

		when(entityManager.contains(task)).thenReturn(true);
		when(entityManager.getReference(Task.class, task.getId())).thenReturn(task);

		dao.deleteTask(task);

		verify(entityManager, times(1)).remove(task);
	}

	@Test
	public final void testDeleteTaskWithoutReference() {
		Task task = list.get(0);

		when(entityManager.contains(task)).thenReturn(false);
		when(entityManager.getReference(Task.class, task.getId())).thenReturn(task);

		dao.deleteTask(task);

		verify(entityManager, times(1)).remove(task);
	}

	@Test
	public final void testSearchTasks() {
		when(entityManager.createQuery("from Task where name like ?1")).thenReturn(query);
		when(query.setParameter(1, "%Task1%")).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		List<Task> tasks = dao.searchTasks("Task1");
		assertEquals(1, tasks.size());
		assertEquals("Task1", tasks.get(0).getName());
	}

	@Test
	public final void testSortTasks() {
		when(entityManager.createQuery("from Task order by employeeId")).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		List<Task> tasks = dao.sortTasks("id");
		assertEquals(1, tasks.size());
	}

	@Test
	public final void testSortTasksByCompleted() {
		when(entityManager.createQuery("from Task where status = 'completed' order by status")).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		List<Task> tasks = dao.sortTasks("completed");
		assertEquals(1, tasks.size());
	}

	@Test
	public final void testSortTasksByPriority() {
		when(entityManager.createQuery("from Task order by priority")).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		List<Task> tasks = dao.sortTasks("priority");
		assertEquals(1, tasks.size());
	}

	@Test
	public final void getTaskListByProject() {
		when(entityManager.createQuery("from Task where Project_ID = ?1")).thenReturn(query);
		when(query.setParameter(1, 1)).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		List<Task> tasks = dao.getTaskListByProject(1);
		assertEquals(1, tasks.size());
	}

	@Test
	public final void getTaskListByProjectWithSort() {
		when(entityManager.createQuery("from Task  where Project_ID = ?1 order by employeeId")).thenReturn(query);
		when(query.setParameter(1, 1)).thenReturn(query);
		when(query.setParameter(1, "id")).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		List<Task> tasks = dao.getTaskListByProjectWithSort(1, "id");
		assertEquals(1, tasks.size());
	}

	@Test
	public final void getTaskListByProjectWithSortCompleted() {
		when(entityManager.createQuery("from Task  where Project_ID = ?1 and status = 'completed' order by status"))
				.thenReturn(query);
		when(query.setParameter(1, 1)).thenReturn(query);
		when(query.setParameter(1, "completed")).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		List<Task> tasks = dao.getTaskListByProjectWithSort(1, "completed");
		assertEquals(1, tasks.size());
	}

	@Test
	public final void getTaskListByProjectWithSortStartDate() {
		when(entityManager.createQuery("from Task  where Project_ID = ?1 order by startDate")).thenReturn(query);
		when(query.setParameter(1, 1)).thenReturn(query);
		when(query.setParameter(1, "startDate")).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		List<Task> tasks = dao.getTaskListByProjectWithSort(1, "startDate");
		assertEquals(1, tasks.size());
	}
}
