package com.example.app.dao;

import java.util.List;

import com.example.app.entity.Project;

public interface ProjectDAO {
	public List<Project> getProjectList();

	public void addProject(Project project);

	void updateProject(Project project);

	List<Project> searchProjects(String searchText);

	List<Project> sortProjects(String sortFlag);

	void deleteProject(Project project);

}
