package com.example.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.dao.ParentTaskDAO;
import com.example.app.dto.ParentTaskDto;
import com.example.app.entity.ParentTask;
import com.example.app.service.ParentTaskService;

@Service
public class ParentTaskServiceImpl extends BaseService implements ParentTaskService {

	private static final Logger logger = LoggerFactory.getLogger(ParentTaskServiceImpl.class);

	@Autowired
	private ParentTaskDAO dao;

	@Override
	public List<ParentTaskDto> getTaskList() {
		ParentTaskDto task = null;
		List<ParentTaskDto> list = new ArrayList<ParentTaskDto>();
		for (ParentTask tempObj : dao.getTaskList()) {
			task = mapper.map(tempObj, ParentTaskDto.class);
			list.add(task);
		}
		return list;
	}

	@Override
	public void addTask(ParentTaskDto taskDto) {
		ParentTask task = mapper.map(taskDto, ParentTask.class);
		dao.addTask(task);
	}

	@Override
	public List<ParentTaskDto> searchTask(String searchText) {
		List<ParentTaskDto> list = new ArrayList<ParentTaskDto>();
		for (ParentTask obj : dao.searchTasks(searchText)) {
			list.add(mapper.map(obj, ParentTaskDto.class));
		}
		return list;
	}

}
