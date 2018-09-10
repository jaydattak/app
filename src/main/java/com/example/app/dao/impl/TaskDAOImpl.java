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
			ParentTask parentTask = entityManager.getReference(ParentTask.class, task.getParentTask());
			task.setParentTask(parentTask);
		}

		entityManager.persist(task);
	}

	@Override
	public void updateTask(Task task) {
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
		if ("id".equals(sortFlag)) {
			sortFlag = "employeeId";
		}
		List<Task> tasks = entityManager.createQuery("from Task order by " + sortFlag).getResultList();
		return tasks;
	}

}
