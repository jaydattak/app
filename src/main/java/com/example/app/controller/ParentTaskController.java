package com.example.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dto.ParentTaskDto;
import com.example.app.service.ParentTaskService;

@RestController
@RequestMapping("/parent/task")
public class ParentTaskController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ParentTaskController.class);

	@Autowired
	private ParentTaskService service;

	@RequestMapping("/list")
	public List<ParentTaskDto> getTaskList() {
		logger.debug("Get TaskList");
		List<ParentTaskDto> tasks = service.getTaskList();
		System.out.println(getAppMessage("greeting"));

		return tasks;
	}

	@RequestMapping("/add")
	public List<ParentTaskDto> addTask(@RequestBody ParentTaskDto task) {
		service.addTask(task);
		return getTaskList();
	}
	
	

}
