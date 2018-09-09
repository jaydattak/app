package com.example.app.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.dao.ParentTaskDAO;
import com.example.app.dto.ParentTaskDto;
import com.example.app.service.ParentTaskService;

@Service
public class ParentTaskServiceImpl extends BaseService implements ParentTaskService {

	private static final Logger logger = LoggerFactory.getLogger(ParentTaskServiceImpl.class);
	
	@Autowired
	private ParentTaskDAO dao;

	@Override
	public List<ParentTaskDto> getTaskList() {
		return dao.getTaskList();
	}

	@Override
	public void addTask(ParentTaskDto task) {
		dao.addTask(task);
	}

}
