package com.example.app.dao;

import java.util.List;

import com.example.app.entity.ParentTask;

public interface ParentTaskDAO {
	public List<ParentTask> getTaskList();

	public void addTask(ParentTask task);

	public List<ParentTask> searchTasks(String searchText);

}
