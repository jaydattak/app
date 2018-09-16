package com.example.app.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.app.dao.ParentTaskDAO;
import com.example.app.dto.ParentTaskDto;
import com.example.app.entity.ParentTask;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
public class ParentTaskServiceImplTest {

	@Mock
	private ParentTaskDAO dao;

	@InjectMocks
	private ParentTaskServiceImpl service;

	@Mock
	private ModelMapper mapper;

	@Mock
	private ObjectMapper jsonMapper;

	List<ParentTask> list = new ArrayList<ParentTask>();

	List<ParentTask> mulipleItemsList = new ArrayList<ParentTask>();

	List<ParentTaskDto> dtoList = new ArrayList<ParentTaskDto>();

	List<ParentTaskDto> dtoMulipleItemsList = new ArrayList<ParentTaskDto>();

	@Before
	public void setUp() throws Exception {
		populateList();
		populateDtoList();
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

	private void populateDtoList() {

		ParentTaskDto taskDto = new ParentTaskDto();
		taskDto.setId(1);
		taskDto.setName("ParentTask1");

		dtoList.add(taskDto);

		dtoMulipleItemsList.add(taskDto);
		taskDto = new ParentTaskDto();
		taskDto.setId(2);
		taskDto.setName("ParentTask2");

		dtoMulipleItemsList.add(taskDto);
	}

	@Test
	public final void testGetParentTaskList() {
		when(dao.getTaskList()).thenReturn(list);

		ParentTask task = list.get(0);
		ParentTaskDto taskDto = dtoList.get(0);

		when(mapper.map(task, ParentTaskDto.class)).thenReturn(taskDto);

		List<ParentTaskDto> tasks = service.getTaskList();

		assertEquals(1, tasks.size());
		assertEquals(1, tasks.get(0).getId());
		assertEquals("ParentTask1", tasks.get(0).getName());
	}

	@Test
	public final void testGetParentTaskListWithMultipleItems() {
		when(dao.getTaskList()).thenReturn(mulipleItemsList);

		ParentTask task = mulipleItemsList.get(0);
		ParentTaskDto taskDto = dtoMulipleItemsList.get(0);

		when(mapper.map(task, ParentTaskDto.class)).thenReturn(taskDto);

		task = mulipleItemsList.get(1);
		taskDto = dtoMulipleItemsList.get(1);

		when(mapper.map(task, ParentTaskDto.class)).thenReturn(taskDto);

		List<ParentTaskDto> tasks = service.getTaskList();

		assertEquals(2, tasks.size());

		assertEquals(1, tasks.get(0).getId());
		assertEquals("ParentTask1", tasks.get(0).getName());

		assertEquals(2, tasks.get(1).getId());
		assertEquals("ParentTask2", tasks.get(1).getName());

	}

	@Test
	public final void testAddParentTask() {
		ParentTask task = new ParentTask();
		task.setId(1);
		task.setName("PR");
		ParentTaskDto taskDto = new ParentTaskDto();
		taskDto.setName("PR");
		when(mapper.map(taskDto, ParentTask.class)).thenReturn(task);
		service.addTask(taskDto);
		verify(dao, times(1)).addTask(task);
	}

	@Test
	public final void testSearchTask() {
		when(dao.searchTasks("Parent")).thenReturn(list);
		ParentTaskDto taskDto = dtoList.get(0);
		ParentTask task = list.get(0);
		when(mapper.map(task, ParentTaskDto.class)).thenReturn(taskDto);
		List<ParentTaskDto> tasks = service.searchTask("Parent");
		assertEquals(1, tasks.size());
		assertEquals(1, tasks.get(0).getId());
		assertEquals("ParentTask1", tasks.get(0).getName());
	}

}
