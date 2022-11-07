package com.spring.Examination.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.Examination.entity.Exam;
import com.spring.Examination.repository.ExamRepository;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamRepository examRepository;

	@Override
	public void save(Exam exam) {
		examRepository.save(exam);
	}

	@Override
	public List<Exam> getExamList() {
		return examRepository.findAll();
	}

	@Override
	public Exam getExamById(int id) {
		return examRepository.findById(id).get();
	}

	@Override
	public List<Exam> getExamListByName(String examName) {
		return examRepository.findByExamNameContaining(examName);
	}

	@Override
	public List<Exam> getExamListByDate(String examDate) {
		return examRepository.findByExamNameContaining(examDate);
	}

	@Override
	public List<Exam> getExamByConditions(String examName, String examDate) {

		List<Exam> searchExamList = new ArrayList<>();
		if (!(examName == null || examName.equals("")) && (examDate == null || examDate.equals(""))) {
			searchExamList = examRepository.findByExamNameContaining(examName);
		} else if ((examName == null || examName.equals("")) && !(examDate == null || examDate.equals(""))) {
			searchExamList = examRepository.findByExamDateContaining(examDate);
		} else {
			searchExamList = examRepository.findByExamNameContainingAndExamDateContaining(examName, examDate);
		}
		return searchExamList;
	}

	@Override
	public List<Exam> getExamByUserId(int id) {
		return examRepository.findExamsByUsersId(id);
	}

	@Override
	public void deleteById(int id) {
		examRepository.deleteById(id);

	}

	@Override
	public void delete(Exam exam) {
		examRepository.delete(exam);
	}

}
