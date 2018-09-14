package com.example.app.dao;

import java.util.List;

import com.example.app.entity.User;

public interface UserDAO {

	public List<User> getUserList();

	public void addUser(User user);

	public void updateUser(User user);

	public void deleteUser(User user);

	public List<User> searchUsers(String searchText);

	List<User> sortUsers(String sortFlag);

}
