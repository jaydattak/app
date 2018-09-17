package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.dto.ProjectDto;
import com.example.app.exception.UserException;

@Service
public interface ProjectService {
	public List<ProjectDto> getProjectList();

	public void addProject(ProjectDto project) throws UserException;

	public void deleteProject(int id) throws UserException;

	public void updateProject(ProjectDto project, int id) throws UserException;

	public List<ProjectDto> searchProject(String searchText);

	public List<ProjectDto> sortProjects(String sortBy);

}
