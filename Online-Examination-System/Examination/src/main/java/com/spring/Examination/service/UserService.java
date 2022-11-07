package com.spring.Examination.service;

import java.util.List;

import com.spring.Examination.entity.User;

public interface UserService {

	public User getLoginUser(String username, String password);

	public void save(User user);

	public List<User> getAllUsers();

	public List<User> getUsersByConditions(String username, String email);

	public User getUserById(int id);

	public void deleteUserById(int id);

	public List<User> getUsersByExamsId(int id);

	public void delete(User user);
}
