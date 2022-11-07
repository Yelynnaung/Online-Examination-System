package com.spring.Examination.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.Examination.entity.ExamUser;
import com.spring.Examination.repository.ExamUserRepository;

@Service
public class ExamUserServiceImpl implements ExamUserService {

	@Autowired
	ExamUserRepository examUserRepository;

	@Override
	public List<ExamUser> getExamUserByUserId(int id) {
		return examUserRepository.findByUserId(id);
	}

	@Override
	public void delete(int id) {
		examUserRepository.deleteById(id);
	}

	@Override
	public void save(ExamUser examUser) {
		examUserRepository.save(examUser);
	}

}
