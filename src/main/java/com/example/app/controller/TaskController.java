package com.example.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dto.TaskDto;
import com.example.app.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController extends BaseController  {

	
	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	@Autowired
	private TaskService service;

	@RequestMapping("/list")
	public List<TaskDto> getTaskList() {
		List<TaskDto> tasks = service.getTaskList();
		return tasks;
	}
	
	@RequestMapping("/add")
	public List<TaskDto> getTaskList(@RequestBody TaskDto task) {
		service.addTask(task);
		return getTaskList();
	}

}
