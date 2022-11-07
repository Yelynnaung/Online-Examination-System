package com.spring.Examination.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exam_result")
public class ExamResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int examId;

	private int userId;

	private String userName;

	private String examName;

	private String examDate;

	private String examHour;

	private String examMin;

	private int durationTime;

	private int passMark;

	private int marks;

	private String passStatus;

	public ExamResult() {
	}

	public ExamResult(int examId, int userId, String userName, String examName, String examDate, String examHour,
			String examMin, int durationTime, int passMark, int marks, String passStatus) {
		super();
		this.examId = examId;
		this.userId = userId;
		this.userName = userName;
		this.examName = examName;
		this.examDate = examDate;
		this.examHour = examHour;
		this.examMin = examMin;
		this.durationTime = durationTime;
		this.passMark = passMark;
		this.marks = marks;
		this.passStatus = passStatus;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}

	public String getExamHour() {
		return examHour;
	}

	public void setExamHour(String examHour) {
		this.examHour = examHour;
	}

	public String getExamMin() {
		return examMin;
	}

	public void setExamMin(String examMin) {
		this.examMin = examMin;
	}

	public int getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(int durationTime) {
		this.durationTime = durationTime;
	}

	public String getPassStatus() {
		return passStatus;
	}

	public void setPassStatus(String passStatus) {
		this.passStatus = passStatus;
	}

	public int getPassMark() {
		return passMark;
	}

	public void setPassMark(int passMark) {
		this.passMark = passMark;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

}
