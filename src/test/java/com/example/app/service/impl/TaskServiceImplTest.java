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

import com.example.app.dao.TaskDAO;
import com.example.app.dto.ParentTaskDto;
import com.example.app.dto.TaskDto;
import com.example.app.entity.ParentTask;
import com.example.app.entity.Task;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
public class TaskServiceImplTest {

	@Mock
	private TaskDAO dao;

	@InjectMocks
	private TaskServiceImpl service;

	@Mock
	private ModelMapper mapper;

	@Mock
	private ObjectMapper jsonMapper;

	List<Task> list = new ArrayList<Task>();

	List<Task> mulipleItemsList = new ArrayList<Task>();

	List<TaskDto> dtoList = new ArrayList<TaskDto>();

	List<TaskDto> dtoMulipleItemsList = new ArrayList<TaskDto>();

	@Before
	public void setUp() throws Exception {
		populateList();
		populateDtoList();
	}

	private void populateList() {
		Task task = new Task();
		task.setId(1);
		task.setName("Task1");
		
		ParentTask parentTask = new ParentTask();
		parentTask.setId(1);
		parentTask.setName("ParentTask1");
		
		task.setParentTask(parentTask);

		list.add(task);

		mulipleItemsList.add(task);

		task = new Task();
		task.setId(2);
		task.setName("Task2");

		task = new Task();
		task.setId(2);
		task.setName("T2");

		mulipleItemsList.add(task);
	}

	private void populateDtoList() {

		TaskDto taskDto = new TaskDto();
		taskDto.setId(1);
		taskDto.setName("Task1");
		
		ParentTaskDto parentTaskDto = new ParentTaskDto();
		parentTaskDto.setId(1);
		parentTaskDto.setName("ParentTask1");
		
		taskDto.setParentTask(parentTaskDto);

		dtoList.add(taskDto);

		dtoMulipleItemsList.add(taskDto);
		taskDto = new TaskDto();
		taskDto.setId(2);
		taskDto.setName("Task2");

		dtoMulipleItemsList.add(taskDto);
	}

	@Test
	public final void testGetTaskList() {
		when(dao.getTaskList()).thenReturn(list);

		Task task = list.get(0);
		TaskDto taskDto = dtoList.get(0);

		when(mapper.map(task, TaskDto.class)).thenReturn(taskDto);

		List<TaskDto> tasks = service.getTaskList();

		assertEquals(1, tasks.size());
		assertEquals(1, tasks.get(0).getId());
		assertEquals("Task1", tasks.get(0).getName());
	}

	@Test
	public final void testGetTaskListWithMultipleItems() {
		when(dao.getTaskList()).thenReturn(mulipleItemsList);

		Task task = mulipleItemsList.get(0);
		TaskDto taskDto = dtoMulipleItemsList.get(0);

		when(mapper.map(task, TaskDto.class)).thenReturn(taskDto);

		task = mulipleItemsList.get(1);
		taskDto = dtoMulipleItemsList.get(1);

		when(mapper.map(task, TaskDto.class)).thenReturn(taskDto);

		List<TaskDto> tasks = service.getTaskList();

		assertEquals(2, tasks.size());

		assertEquals(1, tasks.get(0).getId());
		assertEquals("Task1", tasks.get(0).getName());

		assertEquals(2, tasks.get(1).getId());
		assertEquals("Task2", tasks.get(1).getName());
	}

	@Test
	public final void testAddTask() {
		Task task = list.get(0);
		TaskDto taskDto = dtoList.get(0);
		when(mapper.map(taskDto, Task.class)).thenReturn(task);
		service.addTask(taskDto);
		verify(dao, times(1)).addTask(task);
	}

	@Test
	public final void testDeleteTask() {
		TaskDto taskDto = dtoList.get(0);
		Task task = list.get(0);
		when(mapper.map(taskDto, Task.class)).thenReturn(task);
		service.deleteTask(taskDto.getId());
		verify(dao, times(1)).deleteTask(task);
	}

	@Test
	public final void testUpdateTask() {
		Task task = list.get(0);
		TaskDto taskDto = dtoList.get(0);
		when(mapper.map(taskDto, Task.class)).thenReturn(task);
		service.updateTask(taskDto, taskDto.getId());
		verify(dao, times(1)).updateTask(task);
	}

	@Test
	public final void testSearchTask() {
		when(dao.searchTasks("1")).thenReturn(list);
		Task task = list.get(0);
		TaskDto taskDto = dtoList.get(0);

		when(mapper.map(task, TaskDto.class)).thenReturn(taskDto);

		List<TaskDto> tasks = service.searchTask("1");

		assertEquals(1, tasks.size());
		assertEquals(1, tasks.get(0).getId());
		assertEquals("Task1", tasks.get(0).getName());
	}

	@Test
	public final void testSortTasks() {
		when(dao.sortTasks("name")).thenReturn(list);
		TaskDto taskDto = dtoList.get(0);
		Task task = list.get(0);
		when(mapper.map(task, TaskDto.class)).thenReturn(taskDto);

		List<TaskDto> tasks = service.sortTasks("name");

		assertEquals(1, tasks.size());
		assertEquals(1, task.getId());
		assertEquals("Task1", task.getName());

	}

	@Test
	public final void testGetTaskListByProject() {
		TaskDto taskDto = dtoList.get(0);
		Task task = list.get(0);
		when(dao.getTaskListByProject(taskDto.getId())).thenReturn(list);
		when(mapper.map(task, TaskDto.class)).thenReturn(taskDto);

		List<TaskDto> tasks = service.getTaskListByProject(taskDto.getId());

		assertEquals(1, tasks.size());
		assertEquals(1, task.getId());
		assertEquals("Task1", task.getName());

	}

	@Test
	public final void testGetListByProjectWithSort() {
		TaskDto taskDto = dtoList.get(0);
		Task task = list.get(0);
		when(dao.getTaskListByProjectWithSort(taskDto.getId(), "id")).thenReturn(list);
		when(mapper.map(task, TaskDto.class)).thenReturn(taskDto);

		List<TaskDto> tasks = service.getTaskListByProjectWithSort(taskDto.getId(), "id");

		assertEquals(1, tasks.size());
		assertEquals(1, task.getId());
		assertEquals("Task1", task.getName());

	}

}
