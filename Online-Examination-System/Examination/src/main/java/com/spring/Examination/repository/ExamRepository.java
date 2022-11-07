package com.spring.Examination.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.Examination.entity.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {

	List<Exam> findByExamNameContaining(String examName);

	List<Exam> findByExamDateContaining(String examDate);

	List<Exam> findByExamNameContainingAndExamDateContaining(String examName, String examDate);

	List<Exam> findExamsByUsersId(int id);

}
