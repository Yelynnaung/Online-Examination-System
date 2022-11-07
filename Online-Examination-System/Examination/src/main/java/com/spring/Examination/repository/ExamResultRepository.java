package com.spring.Examination.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.Examination.entity.ExamResult;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Long>{

	List<ExamResult> getByUserId(int id);

	List<ExamResult> getByExamId(int examId);

	List<ExamResult> getByExamIdAndExamDateAndExamHourAndExamMin(int examId, String examDate, String hour, String min);

	List<ExamResult> findByExamNameContaining(String examName);

	List<ExamResult> findByExamDateContaining(String examDate);

	List<ExamResult> findByExamNameContainingAndExamDateContaining(String examName, String examDate);

}
