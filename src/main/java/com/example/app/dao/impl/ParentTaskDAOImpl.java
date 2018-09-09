package com.example.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.app.controller.UserController;
import com.example.app.dao.ParentTaskDAO;
import com.example.app.dto.ParentTaskDto;

@Transactional
@Repository
public class ParentTaskDAOImpl implements ParentTaskDAO {

	private static final Logger logger = LoggerFactory.getLogger(ParentTaskDAOImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<ParentTaskDto> getTaskList() {
		List<ParentTaskDto> tasks = (List<ParentTaskDto>) entityManager.createQuery("from ParentTask").getResultList();
		return tasks;
	}

	@Override
	public void addTask(ParentTaskDto task) {
		entityManager.persist(task);
	}

}
