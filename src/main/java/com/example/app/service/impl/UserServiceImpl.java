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
import com.example.app.exception.UserException;
import com.example.app.service.UserService;

@Service
public class UserServiceImpl extends BaseService implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDAO dao;

	@Override
	public List<UserDto> getUserList() {
		List<UserDto> list = new ArrayList<UserDto>();
		for (User user : dao.getUserList()) {
			list.add(mapper.map(user, UserDto.class));
		}
		logger.debug("List Size : " + list.size());
		return list;
	}

	@Override
	public void addUser(UserDto userdto) throws UserException {
		try {
			User user = mapper.map(userdto, User.class);
			dao.addUser(user);
		} catch (Exception e) {
			throw new UserException(e.getMessage(), e);
		}
	}

	@Override
	public void deleteUser(int id) throws UserException {
		try {
			UserDto userdto = new UserDto();
			userdto.setId(id);
			User user = mapper.map(userdto, User.class);
			dao.deleteUser(user);
		} catch (Exception e) {
			throw new UserException(e.getMessage(), e);
		}
	}

	@Override
	public void updateUser(UserDto userdto, int id) throws UserException {
		try {
			userdto.setId(id);
			User user = mapper.map(userdto, User.class);
			dao.updateUser(user);
		} catch (Exception e) {
			throw new UserException(e.getMessage(), e);
		}
	}

	@Override
	public List<UserDto> searchUser(String searchText) {
		List<UserDto> list = new ArrayList<UserDto>();
		for (User user : dao.searchUsers(searchText)) {
			list.add(mapper.map(user, UserDto.class));
		}
		logger.debug("List Size : " + list.size());
		return list;
	}

	@Override
	public List<UserDto> sortUsers(String flag) {
		List<UserDto> list = new ArrayList<UserDto>();
		for (User user : dao.sortUsers(flag)) {
			list.add(mapper.map(user, UserDto.class));
		}
		logger.debug("List Size : " + list.size());
		return list;
	}

}
