package com.spring.Examination.service;

import java.util.List;

import com.spring.Examination.entity.ExamUser;

public interface ExamUserService {

	void delete(int id);

	List<ExamUser> getExamUserByUserId(int id);

	void save(ExamUser examUser);

}
