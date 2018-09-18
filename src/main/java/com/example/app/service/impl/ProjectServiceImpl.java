package com.example.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.dao.ProjectDAO;
import com.example.app.dto.ProjectDto;
import com.example.app.entity.Project;
import com.example.app.entity.Task;
import com.example.app.exception.UserException;
import com.example.app.service.ProjectService;

@Service
public class ProjectServiceImpl extends BaseService implements ProjectService {

	private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

	@Autowired
	private ProjectDAO dao;

	private int calculateNoOfCompletedTasks(Project project) {
		int count = 0;
		for (Task task : project.getTasks()) {
			if ("completed".equals(task.getStatus())) {
				count++;
			}
		}
		return count;
	}

	@Override
	public List<ProjectDto> getProjectList() {
		ProjectDto project = null;
		List<ProjectDto> list = new ArrayList<ProjectDto>();
		for (Project obj : dao.getProjectList()) {
			project = mapper.map(obj, ProjectDto.class);
			project.setNoOfTasks(obj.getTasks().size());
			project.setNoOfCompletedTasks(calculateNoOfCompletedTasks(obj));
			list.add(project);
			logger.debug("List Size : " + list.size());
		}
		return list;
	}

	@Override
	public void addProject(ProjectDto project) throws UserException {
		try {
			dao.addProject(mapper.map(project, Project.class));
		} catch (Exception e) {
			throw new UserException(e.getMessage(), e);
		}
	}

	@Override
	public void deleteProject(int id) throws UserException {
		try {
			ProjectDto project = new ProjectDto();
			project.setId(id);
			dao.deleteProject(mapper.map(project, Project.class));
		} catch (Exception e) {
			throw new UserException(e.getMessage(), e);
		}

	}

	@Override
	public void updateProject(ProjectDto project, int id) throws UserException {
		try {
			project.setId(id);
			dao.updateProject(mapper.map(project, Project.class));
		} catch (Exception e) {
			throw new UserException(e.getMessage(), e);
		}
	}

	@Override
	public List<ProjectDto> searchProject(String searchText) {
		ProjectDto project = null;
		List<ProjectDto> list = new ArrayList<ProjectDto>();
		for (Project obj : dao.searchProjects(searchText)) {
			project = mapper.map(obj, ProjectDto.class);
			project.setNoOfTasks(obj.getTasks().size());
			project.setNoOfCompletedTasks(calculateNoOfCompletedTasks(obj));
			list.add(project);
			logger.debug("List Size : " + list.size());
		}
		return list;
	}

	@Override
	public List<ProjectDto> sortProjects(String flag) {
		ProjectDto project = null;
		List<ProjectDto> list = new ArrayList<ProjectDto>();
		for (Project obj : dao.sortProjects(flag)) {
			project = mapper.map(obj, ProjectDto.class);
			project.setNoOfTasks(obj.getTasks().size());
			project.setNoOfCompletedTasks(calculateNoOfCompletedTasks(obj));
			list.add(project);
			logger.debug("List Size : " + list.size());
		}
		return list;
	}

}
