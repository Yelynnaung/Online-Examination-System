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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "question")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "question_description")
	private String question_description;

	@Column(name = "correct_answer")
	private String correct_answer;

	@Column(name = "pay_mark")
	private int pay_mark;

	@Column(name = "created_date_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDateTime;

	@Column(name = "updated_date_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDateTime;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "q_id")
	private List<Answer> answers = new ArrayList<>();

	@Transient
	private String answer1;
	@Transient
	private String answer2;
	@Transient
	private String answer3;
	@Transient
	private String answer4;

	public Question() {
	}

	public Question(String question_description, String correct_answer, int pay_mark, Date createdDateTime,
			Date updatedDateTime) {
		super();
		this.question_description = question_description;
		this.correct_answer = correct_answer;
		this.pay_mark = pay_mark;
		this.createdDateTime = createdDateTime;
		this.updatedDateTime = updatedDateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion_description() {
		return question_description;
	}

	public void setQuestion_description(String question_description) {
		this.question_description = question_description;
	}

	public String getCorrect_answer() {
		return correct_answer;
	}

	public void setCorrect_answer(String correct_answer) {
		this.correct_answer = correct_answer;
	}

	public int getPay_mark() {
		return pay_mark;
	}

	public void setPay_mark(int pay_mark) {
		this.pay_mark = pay_mark;
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

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", question_detail=" + question_description + ", correct_answer=" + correct_answer
				+ ", answers=" + answers + "]";
	}

}
