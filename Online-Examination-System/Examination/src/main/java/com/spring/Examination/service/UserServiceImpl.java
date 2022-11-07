package com.spring.Examination.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.Examination.entity.User;
import com.spring.Examination.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User getLoginUser(String username, String password) {
		return userRepository.findByUsernameContainsAndPasswordContains(username, password);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(int id) {
		return userRepository.findById(id).get();
	}

	@Override
	public List<User> getUsersByConditions(String username, String email) {

		List<User> searchUserList = new ArrayList<>();
		if (!(username == null || username.equals("")) && (email == null || email.equals(""))) {
			searchUserList = userRepository.findByUsernameContaining(username);
		} else if ((username == null || username.equals("")) && !(email == null || email.equals(""))) {
			searchUserList = userRepository.findByEmailContaining(email);
		} else {
			searchUserList = userRepository.findByUsernameContainingAndEmailContaining(username, email);
		}
		return searchUserList;
	}

	@Override
	public List<User> getUsersByExamsId(int examId) {
		return userRepository.findUsersByExamsId(examId);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Override
	public void deleteUserById(int id) {
		userRepository.deleteById(id);
	}

}
