package com.example.app.service;

import java.util.List;

import com.example.app.dto.ParentTaskDto;
import com.example.app.exception.UserException;

public interface ParentTaskService {
	public List<ParentTaskDto> getTaskList();

	public void addTask(ParentTaskDto task) throws UserException;

	public List<ParentTaskDto> searchTask(String searchText);

}
