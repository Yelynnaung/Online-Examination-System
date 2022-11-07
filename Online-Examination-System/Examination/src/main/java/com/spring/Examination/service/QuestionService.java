package com.spring.Examination.service;

import java.util.List;

import com.spring.Examination.entity.Question;

public interface QuestionService {
	
	public void save(Question question);
	
	public List<Question> getQuestions();
	
	public Question getQuestion(int id);

	public void deleteQuestion(Question question);

	public void deleteQuestionById(int questionId);
}
