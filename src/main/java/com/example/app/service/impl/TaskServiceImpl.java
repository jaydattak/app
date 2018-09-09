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
		dao.addTask(task);
	}

}
