package com.spring.Examination.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "answer")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "answer_description")
	private String answer_description;

	public Answer() {
	}

	public Answer(String answer_description) {
		this.answer_description = answer_description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnswer_description() {
		return answer_description;
	}

	public void setAnswer_description(String answer_description) {
		this.answer_description = answer_description;
	}
}
