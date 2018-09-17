package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.dto.UserDto;
import com.example.app.exception.UserException;

@Service
public interface UserService {
	public List<UserDto> getUserList();

	public void addUser(UserDto task) throws UserException;

	public void deleteUser(int id) throws UserException;

	public void updateUser(UserDto user, int id) throws UserException;

	public List<UserDto> searchUser(String searchText);

	List<UserDto> sortUsers(String flag);

}
