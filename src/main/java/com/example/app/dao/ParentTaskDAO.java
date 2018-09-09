package com.example.app.dao;

import java.util.List;

import com.example.app.dto.ParentTaskDto;

public interface ParentTaskDAO {
	public List<ParentTaskDto> getTaskList();

	public void addTask(ParentTaskDto task);

}
