package com.example.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.app.dto.UserDto;

@Service
public interface UserService {
	public List<UserDto> getUserList();

	public void addUser(UserDto task);

	public void deleteUser(int id);

	public void updateUser(UserDto user, int id);

	public List<UserDto> searchUser(String searchText);

	List<UserDto> sortUsers(String flag);

}
