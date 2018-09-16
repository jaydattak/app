package com.example.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.app.dao.ParentTaskDAO;
import com.example.app.entity.ParentTask;

@Transactional
@Repository
public class ParentTaskDAOImpl implements ParentTaskDAO {

	private static final Logger logger = LoggerFactory.getLogger(ParentTaskDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<ParentTask> getTaskList() {
		List<ParentTask> tasks = (List<ParentTask>) entityManager.createQuery("from ParentTask").getResultList();
		return tasks;
	}

	@Override
	public void addTask(ParentTask task) {
		entityManager.persist(task);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParentTask> searchTasks(String searchText) {
		logger.debug("SearchText : " + searchText);
		List<ParentTask> tasks = entityManager.createQuery("from ParentTask where name like ?1")
				.setParameter(1, "%" + searchText + "%").getResultList();
		return tasks;
	}
}
