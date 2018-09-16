package com.example.app.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.app.dao.UserDAO;
import com.example.app.entity.User;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {

	private static final Logger logger = LoggerFactory.getLogger(TaskDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserList() {
		List<User> users = entityManager.createQuery("from User ").getResultList();
		return users;
	}

	@Override
	public void addUser(User user) {
		entityManager.persist(user);
	}

	@Override
	public void updateUser(User user) {
		entityManager.merge(user);
	}

	@Override
	public void deleteUser(User user) {
		if (!entityManager.contains(user)) {
			user = entityManager.getReference(User.class, user.getId());
		}
		entityManager.remove(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> searchUsers(String searchText) {
		logger.debug("searchText : " + searchText);
		List<User> users = entityManager.createQuery("from User where firstName like ?1 or lastName like ?2")
				.setParameter(1, "%" + searchText + "%").setParameter(2, "%" + searchText + "%").getResultList();
		return users;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> sortUsers(String sortFlag) {
		logger.debug("sortFlag : " + sortFlag);
		if ("id".equals(sortFlag)) {
			sortFlag = "employeeId";
		}
		List<User> users = entityManager.createQuery("from User order by " + sortFlag).getResultList();
		return users;
	}

}
