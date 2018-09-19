package com.example.app.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.app.dto.ParentTaskDto;
import com.example.app.entity.ParentTask;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
public class ParentTaskDAOImplTest {

	@InjectMocks
	private ParentTaskDAOImpl dao;

	@Mock
	private ModelMapper mapper;

	@Mock
	private ObjectMapper jsonMapper;

	List<ParentTask> list = new ArrayList<ParentTask>();

	List<ParentTask> mulipleItemsList = new ArrayList<ParentTask>();

	List<ParentTaskDto> dtoList = new ArrayList<ParentTaskDto>();

	List<ParentTaskDto> dtoMulipleItemsList = new ArrayList<ParentTaskDto>();

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
		ParentTask task = new ParentTask();
		task.setId(1);
		task.setName("ParentTask1");

		list.add(task);

		mulipleItemsList.add(task);

		task = new ParentTask();
		task.setId(2);
		task.setName("ParentTask2");

		mulipleItemsList.add(task);
	}

	@Test
	public final void testGetParentTaskList() {
		when(entityManager.createQuery("from ParentTask")).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		List<ParentTask> tasks = dao.getTaskList();
		assertEquals(1, tasks.size());
	}

	@Test
	public final void testAddParentTask() {
		ParentTask task = list.get(0);

		when(entityManager.contains(task)).thenReturn(true);
		when(entityManager.getReference(ParentTask.class, task.getId())).thenReturn(task);

		dao.addTask(task);

		verify(entityManager, times(1)).persist(task);
	}

	@Test
	public final void testSearchTasks() {
		when(entityManager.createQuery("from ParentTask where name like ?1")).thenReturn(query);
		when(query.setParameter(1, "%Task1%")).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		List<ParentTask> tasks = dao.searchTasks("Task1");
		assertEquals(1, tasks.size());
		assertEquals("ParentTask1", tasks.get(0).getName());
	}
}
