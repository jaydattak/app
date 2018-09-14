package com.example.app.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.app.dao.UserDAO;
import com.example.app.dto.UserDto;
import com.example.app.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

	@Mock
	private UserDAO dao;

	@InjectMocks
	private UserServiceImpl service;

	@Mock
	private ModelMapper mapper;

	@Mock
	private ObjectMapper jsonMapper;

	List<User> list = new ArrayList<User>();

	List<User> mulipleItemsList = new ArrayList<User>();

	List<UserDto> dtoList = new ArrayList<UserDto>();

	List<UserDto> dtoMulipleItemsList = new ArrayList<UserDto>();

	@Before
	public void setUp() throws Exception {
		populateList();
		populateDtoList();
	}

	private void populateList() {
		User user = new User();
		user.setId(1);
		user.setFirstName("User1");

		list.add(user);

		mulipleItemsList.add(user);

		user = new User();
		user.setId(2);
		user.setFirstName("User2");

		mulipleItemsList.add(user);
	}

	private void populateDtoList() {

		UserDto userDto = new UserDto();
		userDto.setId(1);
		userDto.setFirstName("User1");

		dtoList.add(userDto);

		dtoMulipleItemsList.add(userDto);
		userDto = new UserDto();
		userDto.setId(2);
		userDto.setFirstName("User2");

		dtoMulipleItemsList.add(userDto);
	}

	@Test
	public final void testGetUserList() {
		when(dao.getUserList()).thenReturn(list);

		User user = list.get(0);
		UserDto userDto = dtoList.get(0);

		when(mapper.map(user, UserDto.class)).thenReturn(userDto);

		List<UserDto> users = service.getUserList();

		assertEquals(1, users.size());
		assertEquals(1, users.get(0).getId());
		assertEquals("User1", users.get(0).getFirstName());
	}

	@Test
	public final void testGetUserListWithMultipleItems() {
		when(dao.getUserList()).thenReturn(mulipleItemsList);

		User user = mulipleItemsList.get(0);
		UserDto userDto = dtoMulipleItemsList.get(0);

		when(mapper.map(user, UserDto.class)).thenReturn(userDto);

		user = mulipleItemsList.get(1);
		userDto = dtoMulipleItemsList.get(1);

		when(mapper.map(user, UserDto.class)).thenReturn(userDto);

		List<UserDto> users = service.getUserList();

		assertEquals(2, users.size());

		assertEquals(1, users.get(0).getId());
		assertEquals("User1", users.get(0).getFirstName());

		assertEquals(2, users.get(1).getId());
		assertEquals("User2", users.get(1).getFirstName());

	}

	@Test
	public final void testAddUser() {
		User user = new User();
		user.setId(1);
		user.setFirstName("PR");
		UserDto userDto = new UserDto();
		userDto.setFirstName("PR");
		when(mapper.map(userDto, User.class)).thenReturn(user);
		service.addUser(userDto);
		verify(dao, times(1)).addUser(user);
	}

	@Test
	public final void testDeleteUser() {
		User user = new User();
		user.setId(1);
		user.setFirstName("PR");
		UserDto userDto = new UserDto();
		userDto.setId(1);
		userDto.setFirstName("PR");
		when(mapper.map(userDto, User.class)).thenReturn(user);
		service.deleteUser(1);
		verify(dao, times(1)).deleteUser(user);
	}

	@Test
	public final void testUpdateUser() {
		User user = new User();
		user.setId(1);
		user.setFirstName("PR");
		UserDto userDto = new UserDto();
		userDto.setFirstName("PR");
		when(mapper.map(userDto, User.class)).thenReturn(user);
		service.updateUser(userDto, 1);
		verify(dao, times(1)).updateUser(user);
	}

	@Test
	public final void testSearchUser() {
		when(dao.searchUsers("1")).thenReturn(list);
		User user = list.get(0);
		UserDto userDto = dtoList.get(0);

		when(mapper.map(user, UserDto.class)).thenReturn(userDto);

		List<UserDto> users = service.searchUser("1");

		assertEquals(1, users.size());
		assertEquals(1, users.get(0).getId());
		assertEquals("User1", users.get(0).getFirstName());
	}

	@Test
	public final void testSortUsers() {
		when(dao.sortUsers("name")).thenReturn(list);
		UserDto userDto = dtoList.get(0);
		User user = list.get(0);
		when(mapper.map(user, UserDto.class)).thenReturn(userDto);

		List<UserDto> users = service.sortUsers("name");

		assertEquals(1, users.size());
		assertEquals(1, user.getId());
		assertEquals("User1", user.getFirstName());

	}

}
