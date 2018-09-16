package com.example.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.app.dao.ProjectDAO;
import com.example.app.entity.Project;
import com.example.app.entity.User;

@Transactional
@Repository
public class ProjectDAOImpl implements ProjectDAO {

	private static final Logger logger = LoggerFactory.getLogger(ProjectDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getProjectList() {
		List<Project> projects = (List<Project>) entityManager.createQuery("from Project").getResultList();
		return projects;
	}

	@Override
	public void addProject(Project project) {
		if (!entityManager.contains(project.getManager())) {
			User user = entityManager.getReference(User.class, project.getManager().getId());
			project.setManager(user);
		}
		entityManager.persist(project);
	}

	@Override
	public void updateProject(Project project) {
		entityManager.merge(project);
	}

	@Override
	public void deleteProject(Project project) {
		if (!entityManager.contains(project)) {
			project = entityManager.getReference(Project.class, project.getId());
		}
		entityManager.remove(project);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> searchProjects(String searchText) {
		logger.debug("SearchText : " + searchText);
		List<Project> projects = entityManager.createQuery("from Project where name like ?1")
				.setParameter(1, "%" + searchText + "%").getResultList();
		return projects;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> sortProjects(String sortFlag) {
		logger.debug("sortFlag : " + sortFlag);
		if ("id".equals(sortFlag)) {
			sortFlag = "employeeId";
		}
		List<Project> projects = entityManager.createQuery("from Project order by " + sortFlag).getResultList();
		return projects;
	}

}
