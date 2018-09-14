package com.example.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.app.dao.TaskDAO;
import com.example.app.entity.ParentTask;
import com.example.app.entity.Project;
import com.example.app.entity.Task;
import com.example.app.entity.User;
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
		Project project = entityManager.getReference(Project.class, task.getProject().getId());
		task.setProject(project);
		User user = entityManager.getReference(User.class, task.getUser().getId());
		task.setUser(user);

		if (task.getParentTask() != null) {
			ParentTask parentTask = entityManager.getReference(ParentTask.class, task.getParentTask().getId());
			task.setParentTask(parentTask);
		}

		entityManager.persist(task);
	}

	@Override
	public void updateTask(Task task) {
		Project project = entityManager.getReference(Project.class, task.getProject().getId());
		task.setProject(project);
		User user = entityManager.getReference(User.class, task.getUser().getId());
		task.setUser(user);

		if (task.getParentTask() != null) {
			ParentTask parentTask = entityManager.getReference(ParentTask.class, task.getParentTask().getId());
			task.setParentTask(parentTask);
		}
		entityManager.merge(task);
	}

	@Override
	public void deleteTask(Task task) {
		if (!entityManager.contains(task)) {
			task = entityManager.getReference(Task.class, task.getId());
		}
		entityManager.remove(task);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> searchTasks(String searchText) {
		List<Task> tasks = entityManager.createQuery("from Task where name like ?1")
				.setParameter(1, "%" + searchText + "%").getResultList();
		return tasks;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> sortTasks(String sortFlag) {
		String query = "order by ";
		if ("id".equals(sortFlag)) {
			query += "employeeId";
		} else if ("completed".equalsIgnoreCase(sortFlag)) {
			query = "where status = 'completed' order by status";
		} else {
			query += sortFlag;
		}
		List<Task> tasks = entityManager.createQuery("from Task " + query).getResultList();
		return tasks;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> getTaskListByProject(int id) {
		List<Task> tasks = (List<Task>) entityManager.createQuery("from Task where Project_ID = ?1").setParameter(1, id)
				.getResultList();
		return tasks;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> getTaskListByProjectWithSort(int id, String sortBy) {
		String query = " where Project_ID = ?1";
		if ("id".equals(sortBy)) {
			query += " order by employeeId";
		} else if ("completed".equalsIgnoreCase(sortBy)) {
			query += " and status = 'completed' order by status";
		} else {
			query += " order by " + sortBy;
		}
		List<Task> tasks = (List<Task>) entityManager.createQuery("from Task " + query).setParameter(1, id)
				.getResultList();
		return tasks;
	}
}
