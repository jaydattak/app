package com.example.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.dao.TaskDAO;
import com.example.app.dto.TaskDto;
import com.example.app.entity.Task;
import com.example.app.exception.UserException;
import com.example.app.service.TaskService;

@Service
public class TaskServiceImpl extends BaseService implements TaskService {

	private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

	@Autowired
	private TaskDAO dao;

	@Override
	public List<TaskDto> getTaskList() {
		logger.debug("getTaskList");
		TaskDto task = null;
		List<TaskDto> list = new ArrayList<TaskDto>();
		for (Task tempObj : dao.getTaskList()) {
			task = mapper.map(tempObj, TaskDto.class);
			list.add(task);
			logger.debug("List Size : " + list.size());
		}
		return list;
	}

	@Override
	public void addTask(TaskDto taskDto) throws UserException {
		try {
			Task task = mapper.map(taskDto, Task.class);
			if (taskDto.isMainTask()) {
				task.setParentTask(null);
			}
			dao.addTask(task);
		} catch (Exception e) {
			throw new UserException(e.getMessage(), e);
		}
	}

	@Override
	public void deleteTask(int id) throws UserException {
		try {
			TaskDto taskDto = new TaskDto();
			taskDto.setId(id);
			Task task = mapper.map(taskDto, Task.class);
			dao.deleteTask(task);
		} catch (Exception e) {
			throw new UserException(e.getMessage(), e);
		}
	}

	@Override
	public void updateTask(TaskDto task, int id) throws UserException {
		try {
			task.setId(id);
			if (task.getParentTask() != null && task.getParentTask().getId() == 0) {
				task.setParentTask(null);
			}
			dao.updateTask(mapper.map(task, Task.class));
		} catch (Exception e) {
			throw new UserException(e.getMessage(), e);
		}
	}

	@Override
	public List<TaskDto> searchTask(String searchText) {
		List<TaskDto> list = new ArrayList<TaskDto>();
		for (Task obj : dao.searchTasks(searchText)) {
			list.add(mapper.map(obj, TaskDto.class));
			logger.debug("List Size : " + list.size());
		}
		return list;
	}

	@Override
	public List<TaskDto> sortTasks(String flag) {
		List<TaskDto> list = new ArrayList<TaskDto>();
		for (Task obj : dao.sortTasks(flag)) {
			list.add(mapper.map(obj, TaskDto.class));
			logger.debug("List Size : " + list.size());
		}
		return list;
	}

	@Override
	public List<TaskDto> getTaskListByProject(int id) {
		TaskDto task = null;
		List<TaskDto> list = new ArrayList<TaskDto>();
		for (Task tempObj : dao.getTaskListByProject(id)) {
			task = mapper.map(tempObj, TaskDto.class);
			list.add(task);
			logger.debug("List Size : " + list.size());
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
			logger.debug("List Size : " + list.size());
		}
		return list;
	}

}
