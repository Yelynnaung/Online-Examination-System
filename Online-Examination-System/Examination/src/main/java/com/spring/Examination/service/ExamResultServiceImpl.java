package com.spring.Examination.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.Examination.entity.ExamResult;
import com.spring.Examination.repository.ExamResultRepository;

@Service
public class ExamResultServiceImpl implements ExamResultService {

	@Autowired
	private ExamResultRepository examResultRepository;

	@Override
	public void save(ExamResult examResult) {
		examResultRepository.save(examResult);
	}

	@Override
	public List<ExamResult> getExamResultList() {
		return examResultRepository.findAll();
	}

	@Override
	public List<ExamResult> getExamResultListByUserId(int userId) {
		return examResultRepository.getByUserId(userId);
	}

	@Override
	public List<ExamResult> getExamResultListByExamIdAndExamDateAndHourAndMin(int examId, String examDate, String hour,
			String min) {
		return examResultRepository.getByExamIdAndExamDateAndExamHourAndExamMin(examId,examDate,hour,min);
	}

	@Override
	public List<ExamResult> getExamResultByConditions(String examName, String examDate) {
		List<ExamResult> examResultList = new ArrayList<>();
		if (!(examName == null || examName.equals("")) && (examDate == null || examDate.equals(""))) {
			examResultList = examResultRepository.findByExamNameContaining(examName);
		} else if ((examName == null || examName.equals("")) && !(examDate == null || examDate.equals(""))) {
			examResultList = examResultRepository.findByExamDateContaining(examDate);
		} else {
			examResultList = examResultRepository.findByExamNameContainingAndExamDateContaining(examName, examDate);
		}
		return examResultList;
	}

}
