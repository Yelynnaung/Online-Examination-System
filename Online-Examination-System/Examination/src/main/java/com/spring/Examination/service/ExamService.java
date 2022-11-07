package com.spring.Examination.service;

import java.util.List;

import com.spring.Examination.entity.Exam;

public interface ExamService {

	public void save(Exam exam);

	public List<Exam> getExamList();

	public Exam getExamById(int id);

	public List<Exam> getExamListByName(String examName);

	List<Exam> getExamListByDate(String examDate);

	public List<Exam> getExamByConditions(String examName,String examDate);

	public List<Exam> getExamByUserId(int id);

	public void deleteById(int id);

	public void delete(Exam exam);

}
