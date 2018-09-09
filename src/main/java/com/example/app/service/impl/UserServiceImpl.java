package com.example.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.dao.UserDAO;
import com.example.app.dto.UserDto;
import com.example.app.entity.User;
import com.example.app.service.UserService;

@Service
public class UserServiceImpl extends BaseService implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDAO dao;

	@Override
	public List<UserDto> getUserList() {
		List<UserDto> users = new ArrayList<UserDto>();
		for (User user : dao.getUserList()) {
			users.add(mapper.map(user, UserDto.class));
		}
		return users;
	}

	@Override
	public void addUser(UserDto userdto) {
		User user = mapper.map(userdto, User.class);
		dao.addUser(user);
	}

	@Override
	public void deleteUser(int id) {
		UserDto userdto = new UserDto();
		userdto.setId(id);
		User user = mapper.map(userdto, User.class);
		dao.deleteUser(user);
	}

	@Override
	public void updateUser(UserDto userdto, int id) {
		userdto.setId(id);
		User user = mapper.map(userdto, User.class);
		dao.updateUser(user);
	}

	@Override
	public List<UserDto> searchUser(String searchText) {
		List<UserDto> users = new ArrayList<UserDto>();
		for (User user : dao.searchUsers(searchText)) {
			users.add(mapper.map(user, UserDto.class));
		}
		return users;
	}

	@Override
	public List<UserDto> sortUsers(String flag) {
		List<UserDto> users = new ArrayList<UserDto>();
		for (User user : dao.sortUsers(flag)) {
			users.add(mapper.map(user, UserDto.class));
		}
		return users;
	}

}
