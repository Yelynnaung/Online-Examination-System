package com.spring.Examination.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.Examination.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByUsernameContainsAndPasswordContains(String username, String password);

	List<User> findByUsernameContaining(String username);
	
	List<User> findByEmailContaining(String email);

	List<User> findByUsernameContainingAndEmailContaining(String username, String email);

	List<User> findUsersByExamsId(int id);
}
