package com.spring.Examination.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.Examination.entity.ExamUser;

@Repository
public interface ExamUserRepository extends JpaRepository<ExamUser, Integer>{

	void deleteByUserId(int id);

	List<ExamUser> findByUserId(int id);

	ExamUser findByUserIdAndExamId(int userId, int examId);

}
