package com.spring.Examination.service;

import java.util.List;

import com.spring.Examination.entity.ExamResult;

public interface ExamResultService {

	void save(ExamResult examResult);

	List<ExamResult> getExamResultList();

	List<ExamResult> getExamResultListByUserId(int id);

	List<ExamResult> getExamResultListByExamIdAndExamDateAndHourAndMin(int examId, String examDate, String hour, String min);

	List<ExamResult> getExamResultByConditions(String examName, String examDate);


}
