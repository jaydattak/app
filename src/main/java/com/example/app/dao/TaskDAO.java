package com.example.app.dao;

import java.util.List;

import com.example.app.entity.Task;

public interface TaskDAO {
	public List<Task> getTaskList();

	public void addTask(Task project);

}
