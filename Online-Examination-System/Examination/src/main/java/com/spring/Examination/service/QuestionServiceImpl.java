package com.spring.Examination.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.Examination.entity.Question;
import com.spring.Examination.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionRepository questionRepository;

	public void save(Question question) {
		questionRepository.save(question);
	}

	@Override
	public List<Question> getQuestions() {
		return questionRepository.findAll();
	}

	@Override
	public Question getQuestion(int id) {
		return questionRepository.findById(id).get();
	}

	@Override
	public void deleteQuestion(Question question) {
		questionRepository.delete(question);
	}

	@Override
	public void deleteQuestionById(int questionId) {
		questionRepository.deleteById(questionId);
	}

}
