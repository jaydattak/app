package com.example.app.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.app.dto.UserDto;
import com.example.app.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
public class UserDAOImplTest {

	@InjectMocks
	private UserDAOImpl dao;

	@Mock
	private ModelMapper mapper;

	@Mock
	private ObjectMapper jsonMapper;

	List<User> list = new ArrayList<User>();

	List<User> mulipleItemsList = new ArrayList<User>();

	List<UserDto> dtoList = new ArrayList<UserDto>();

	List<UserDto> dtoMulipleItemsList = new ArrayList<UserDto>();

	@Mock
	private EntityManager entityManager;

	@Mock
	private Query query;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		populateList();
	}

	private void populateList() {
		User user = new User();
		user.setId(1);
		user.setFirstName("User1");
		user.setLastName("L1");

		list.add(user);

		mulipleItemsList.add(user);

		user = new User();
		user.setId(2);
		user.setFirstName("User2");
		user.setLastName("L2");

		mulipleItemsList.add(user);
	}

	@Test
	public final void testGetUserList() {
		when(entityManager.createQuery("from User ")).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		List<User> users = dao.getUserList();
		assertEquals(1, users.size());
	}

	@Test
	public final void testAddUser() {
		User user = list.get(0);
		when(entityManager.contains(user)).thenReturn(true);
		when(entityManager.getReference(User.class, user.getId())).thenReturn(user);

		dao.addUser(user);

		verify(entityManager, times(1)).persist(user);
	}

	@Test
	public final void testUpdateUser() {
		User user = list.get(0);

		dao.updateUser(user);

		verify(entityManager, times(1)).merge(user);
	}

	@Test
	public final void testDeleteUser() {
		User user = list.get(0);
		when(entityManager.contains(user)).thenReturn(true);
		when(entityManager.getReference(User.class, user.getId())).thenReturn(user);

		dao.deleteUser(user);

		verify(entityManager, times(1)).remove(user);
	}
	
	@Test
	public final void testDeleteUserWithoutReference() {
		User user = list.get(0);
		when(entityManager.contains(user)).thenReturn(false);
		when(entityManager.getReference(User.class, user.getId())).thenReturn(user);

		dao.deleteUser(user);

		verify(entityManager, times(1)).remove(user);
	}

	@Test
	public final void testSearchUsers() {
		when(entityManager.createQuery("from User where firstName like ?1 or lastName like ?2")).thenReturn(query);
		when(query.setParameter(1, "%User1%")).thenReturn(query);
		when(query.setParameter(2, "%User1%")).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		List<User> users = dao.searchUsers("User1");
		assertEquals(1, users.size());
		assertEquals("User1", users.get(0).getFirstName());
	}

	@Test
	public final void testSortUsers() {
		when(entityManager.createQuery("from User order by employeeId")).thenReturn(query);
		when(query.getResultList()).thenReturn(list);
		List<User> users = dao.sortUsers("id");
		assertEquals(1, users.size());
	}

}
