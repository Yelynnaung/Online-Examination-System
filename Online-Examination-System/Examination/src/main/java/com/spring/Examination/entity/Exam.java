package com.spring.Examination.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "exam")
public class Exam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "exam_name")
	private String examName;

	@Column(name = "exam_date")
	private String examDate;

	@Column(name = "exam_hour")
	private String examHour;

	@Column(name = "exam_minute")
	private String examMinute;

	@Column(name = "duration_time")
	private int durationTime;

	@Column(name = "pass_mark")
	private int passMark;

	@Column(name = "created_date_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDateTime;

	@Column(name = "updated_date_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDateTime;

	@ManyToMany
	@JoinTable(name = "exam_user", joinColumns = { @JoinColumn(name = "examId") }, inverseJoinColumns = {
			@JoinColumn(name = "userId") })
	private List<User> users = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "exam_id")
	private List<Question> questions = new ArrayList<>();

	public Exam() {
	}

	public Exam(String examName, String examDate, String examHour, String examMinute, int durationTime, int passMark,
			Date createdDateTime, Date updatedDateTime) {
		super();
		this.examName = examName;
		this.examDate = examDate;
		this.examHour = examHour;
		this.examMinute = examMinute;
		this.durationTime = durationTime;
		this.passMark = passMark;
		this.createdDateTime = createdDateTime;
		this.updatedDateTime = updatedDateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getExamMinute() {
		return examMinute;
	}

	public void setExamMinute(String examMinute) {
		this.examMinute = examMinute;
	}

	public int getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(int durationTime) {
		this.durationTime = durationTime;
	}

	public int getPassMark() {
		return passMark;
	}

	public void setPassMark(int passMark) {
		this.passMark = passMark;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

}
