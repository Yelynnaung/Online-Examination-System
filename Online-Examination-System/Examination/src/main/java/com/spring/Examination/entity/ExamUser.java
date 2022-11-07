package com.spring.Examination.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exam_user")
public class ExamUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "examId")
	private int examId;

	@Column(name = "userId")
	private int userId;

	@Column(name = "answeredStatus")
	private String answeredStatus;

	public ExamUser() {
	}

	public ExamUser(int examId, int userId, String answeredStatus) {
		super();
		this.examId = examId;
		this.userId = userId;
		this.answeredStatus = answeredStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getExamId() {
		return examId;
	}

	public void setExamId(int examId) {
		this.examId = examId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAnsweredStatus() {
		return answeredStatus;
	}

	public void setAnsweredStatus(String answeredStatus) {
		this.answeredStatus = answeredStatus;
	}

}
