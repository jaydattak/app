package com.example.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.internal.bytebuddy.asm.Advice.Enter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.dao.TaskDAO;
import com.example.app.dto.TaskDto;
import com.example.app.entity.Project;
import com.example.app.entity.Task;
import com.example.app.service.TaskService;

@Service
public class TaskServiceImpl extends BaseService implements TaskService {

	private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

	@Autowired
	private TaskDAO dao;

	@Override
	public List<TaskDto> getTaskList() {
		TaskDto task = null;
		List<TaskDto> list = new ArrayList<TaskDto>();
		for (Task tempObj : dao.getTaskList()) {
			task = mapper.map(tempObj, TaskDto.class);
			list.add(task);
		}
		return list;
	}

	@Override
	public void addTask(TaskDto taskDto) {
		Task task = mapper.map(taskDto, Task.class);
		if (!taskDto.isMainTask()) {
			task.setParentTask(null);
		}
		dao.addTask(task);
	}

	@Override
	public void deleteTask(int id) {
		TaskDto task = new TaskDto();
		task.setId(id);
		dao.deleteTask(mapper.map(task, Task.class));
	}

	@Override
	public void updateTask(TaskDto task, int id) {
		task.setId(id);
		if (task.getParentTask().getId() == 0) {
			task.setParentTask(null);
		}
		dao.updateTask(mapper.map(task, Task.class));
	}

	@Override
	public List<TaskDto> searchTask(String searchText) {
		List<TaskDto> list = new ArrayList<TaskDto>();
		for (Task obj : dao.searchTasks(searchText)) {
			list.add(mapper.map(obj, TaskDto.class));
		}
		return list;
	}

	@Override
	public List<TaskDto> sortTasks(String flag) {
		List<TaskDto> list = new ArrayList<TaskDto>();
		for (Task obj : dao.sortTasks(flag)) {
			list.add(mapper.map(obj, TaskDto.class));
		}
		return list;
	}

	@Override
	public List<TaskDto> getTaskListByProject(String id) {
		TaskDto task = null;
		List<TaskDto> list = new ArrayList<TaskDto>();
		for (Task tempObj : dao.getTaskListByProject(id)) {
			task = mapper.map(tempObj, TaskDto.class);
			list.add(task);
		}
		return list;
	}

	@Override
	public List<TaskDto> getTaskListByProjectWithSort(int id, String sortBy) {
		TaskDto task = null;
		List<TaskDto> list = new ArrayList<TaskDto>();
		for (Task tempObj : dao.getTaskListByProjectWithSort(id, sortBy)) {
			task = mapper.map(tempObj, TaskDto.class);
			list.add(task);
		}
		return list;
	}

}
