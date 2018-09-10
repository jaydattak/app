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
import com.example.app.dto.ParentTaskDto;
import com.example.app.dto.ResponseMessage;
import com.example.app.service.ParentTaskService;

@RestController
@RequestMapping("/parenttask")
public class ParentTaskController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ParentTaskController.class);

	@Autowired
	private ParentTaskService service;

	@RequestMapping("/list")
	public List<ParentTaskDto> getTaskList() {
		logger.debug("Get TaskList");
		List<ParentTaskDto> tasks = service.getTaskList();
		return tasks;
	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResponseMessage addParentTask(@RequestBody ParentTaskDto task) {
		System.out.println("I m here");
		try {
			service.addTask(task);
		} catch (Exception e) {
			return new ResponseMessage(false, getAppMessage(ConstantsIf.SAVE_ERROR), e);
		}
		return new ResponseMessage(true, getAppMessage(ConstantsIf.SAVE_SUCCESS));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseMessage deleteTask(@PathVariable(name = "id") int id) {
		try {
			service.deleteTask(id);
		} catch (Exception e) {
			return new ResponseMessage(false, getAppMessage(ConstantsIf.DELETE_ERROR), e);
		}
		return new ResponseMessage(true, getAppMessage(ConstantsIf.DELETE_SUCCESS));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public ResponseMessage updateTask(@PathVariable(name = "id") int id, @RequestBody ParentTaskDto project) {
		try {
			service.updateTask(project, id);
		} catch (Exception e) {
			return new ResponseMessage(false, getAppMessage(ConstantsIf.UPDATE_ERROR), e);
		}
		return new ResponseMessage(true, getAppMessage(ConstantsIf.UPDATE_SUCCESS));
	}

	@RequestMapping(path = "/search/{searchText}", method = RequestMethod.GET)
	public List<ParentTaskDto> searchTask(@PathVariable(name = "searchText") String searchText) {
		return service.searchTask(searchText);
	}

}
