package com.example.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.app.dao.TaskDAO;
import com.example.app.entity.Task;

@Transactional
@Repository
public class TaskDAOImpl implements TaskDAO {

	private static final Logger logger = LoggerFactory.getLogger(TaskDAOImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> getTaskList() {
		List<Task> tasks = (List<Task>) entityManager.createQuery("from Task").getResultList();
		return tasks;
	}

	@Override
	public void addTask(Task task) {
		entityManager.persist(task);
	}

}
