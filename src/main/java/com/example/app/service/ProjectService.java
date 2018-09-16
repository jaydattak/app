package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.dto.ProjectDto;

@Service
public interface ProjectService {
	public List<ProjectDto> getProjectList();

	public void addProject(ProjectDto project) throws Exception;

	public void deleteProject(int id);

	public void updateProject(ProjectDto project, int id);

	public List<ProjectDto> searchProject(String searchText);

	public List<ProjectDto> sortProjects(String sortBy);

}
