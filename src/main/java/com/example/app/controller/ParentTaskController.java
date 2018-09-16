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

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public List<ParentTaskDto> getTaskList() {
		logger.debug("Get TaskList");
		List<ParentTaskDto> tasks = service.getTaskList();
		return tasks;
	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResponseMessage addParentTask(@RequestBody ParentTaskDto task) {
		try {
			service.addTask(task);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return new ResponseMessage(false, getAppMessage(ConstantsIf.SAVE_ERROR), e);
		}
		return new ResponseMessage(true, getAppMessage(ConstantsIf.SAVE_SUCCESS));
	}

	@RequestMapping(path = "/search/{searchText}", method = RequestMethod.GET)
	public List<ParentTaskDto> searchTask(@PathVariable(name = "searchText") String searchText) {
		return service.searchTask(searchText);
	}

}
