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
import com.example.app.service.ProjectService;

@Service
public class ProjectServiceImpl extends BaseService implements ProjectService {

	private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

	@Autowired
	private ProjectDAO dao;

	@Override
	public List<ProjectDto> getProjectList() {
		ProjectDto project = null;
		List<ProjectDto> list = new ArrayList<ProjectDto>();
		for (Project obj : dao.getProjectList()) {
			project = mapper.map(obj, ProjectDto.class);
			project.setNoOfTasks(obj.getTasks().size());
			list.add(project);
		}
		return list;
	}

	@Override
	public void addProject(ProjectDto project) {
		dao.addProject(mapper.map(project, Project.class));
	}

	@Override
	public void deleteProject(int id) {
		ProjectDto project = new ProjectDto();
		project.setId(id);
		dao.deleteProject(mapper.map(project, Project.class));
	}

	@Override
	public void updateProject(ProjectDto project, int id) {
		project.setId(id);
		dao.updateProject(mapper.map(project, Project.class));
	}

	@Override
	public List<ProjectDto> searchProject(String searchText) {
		List<ProjectDto> list = new ArrayList<ProjectDto>();
		for (Project obj : dao.searchProjects(searchText)) {
			list.add(mapper.map(obj, ProjectDto.class));
		}
		return list;
	}

	@Override
	public List<ProjectDto> sortProjects(String flag) {
		List<ProjectDto> list = new ArrayList<ProjectDto>();
		for (Project obj : dao.sortProjects(flag)) {
			list.add(mapper.map(obj, ProjectDto.class));
		}
		return list;
	}

}
