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
import com.example.app.dto.ProjectDto;
import com.example.app.dto.ResponseMessage;
import com.example.app.service.ProjectService;

@RestController
@RequestMapping(path = "/project")
public class ProjectController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	@Autowired
	private ProjectService service;

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public List<ProjectDto> getProjectList() {
		List<ProjectDto> projects = service.getProjectList();
		return projects;
	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public ResponseMessage addProject(@RequestBody ProjectDto project) {
		try {
			service.addProject(project);
		} catch (Exception e) {
			return new ResponseMessage(false, getAppMessage(ConstantsIf.SAVE_ERROR), e);
		}
		return new ResponseMessage(true, getAppMessage(ConstantsIf.SAVE_SUCCESS));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseMessage deleteProject(@PathVariable(name = "id") int id) {
		try {
			service.deleteProject(id);
		} catch (Exception e) {
			return new ResponseMessage(false, getAppMessage(ConstantsIf.DELETE_ERROR), e);
		}
		return new ResponseMessage(true, getAppMessage(ConstantsIf.DELETE_SUCCESS));
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public ResponseMessage updateProject(@PathVariable(name = "id") int id, @RequestBody ProjectDto project) {
		try {
			service.updateProject(project, id);
		} catch (Exception e) {
			return new ResponseMessage(false, getAppMessage(ConstantsIf.UPDATE_ERROR), e);
		}
		return new ResponseMessage(true, getAppMessage(ConstantsIf.UPDATE_SUCCESS));
	}

	@RequestMapping(path = "/search/{searchText}", method = RequestMethod.GET)
	public List<ProjectDto> searchProject(@PathVariable(name = "searchText") String searchText) {
		return service.searchProject(searchText);
	}

	@RequestMapping(path = "/sort/{sortBy}", method = RequestMethod.GET)
	public List<ProjectDto> sortProjects(@PathVariable(name = "sortBy") String sortBy) {
		return service.sortProjects(sortBy);
	}
}
