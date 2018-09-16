package com.example.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.constants.ConstantsIf;
import com.example.app.dto.ResponseMessage;
import com.example.app.dto.TaskDto;
import com.example.app.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private TaskService service;

	@RequestMapping(path = "/list",  method = RequestMethod.GET)
	public List<TaskDto> getTaskList() {
		logger.debug("getTaskList");
		List<TaskDto> tasks = service.getTaskList();
		return tasks;
	}

	@RequestMapping(path = "/add",  method = RequestMethod.POST)
	public ResponseMessage addTask(@RequestBody TaskDto task) {
		try {
			service.addTask(task);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return new ResponseMessage(false, getAppMessage(ConstantsIf.SAVE_ERROR), e);
		}
		return new ResponseMessage(true, getAppMessage(ConstantsIf.SAVE_SUCCESS));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseMessage deleteTask(@PathVariable(name = "id") int id) {
		try {
			service.deleteTask(id);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return new ResponseMessage(false, getAppMessage(ConstantsIf.DELETE_ERROR), e);
		}
		return new ResponseMessage(true, getAppMessage(ConstantsIf.DELETE_SUCCESS));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public ResponseMessage updateTask(@PathVariable(name = "id") int id, @RequestBody TaskDto project) {
		try {
			service.updateTask(project, id);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return new ResponseMessage(false, getAppMessage(ConstantsIf.UPDATE_ERROR), e);
		}
		return new ResponseMessage(true, getAppMessage(ConstantsIf.UPDATE_SUCCESS));
	}

	@RequestMapping(path = "/search/{searchText}", method = RequestMethod.GET)
	public List<TaskDto> searchTask(@PathVariable(name = "searchText") String searchText) {
		return service.searchTask(searchText);
	}

	@RequestMapping(path = "/sort/{sortBy}", method = RequestMethod.GET)
	public List<TaskDto> sortTasks(@PathVariable(name = "sortBy") String sortBy) {
		return service.sortTasks(sortBy);
	}
	
	@RequestMapping(path = "/list/project/{id}", method = RequestMethod.GET)
	public List<TaskDto> getTaskListByProject(@PathVariable(name = "id") int id) {
		List<TaskDto> tasks = service.getTaskListByProject(id);
		return tasks;
	}
	
	@RequestMapping(path = "/sort/{id}/{sortBy}", method = RequestMethod.GET)
	public List<TaskDto> getTaskListByProjectWithSort(@PathVariable(name = "id") int id, @PathVariable(name = "sortBy") String sortBy) {
		List<TaskDto> tasks = service.getTaskListByProjectWithSort(id, sortBy);
		return tasks;
	}



}
