package com.spring.Examination.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.Examination.entity.Answer;
import com.spring.Examination.entity.Exam;
import com.spring.Examination.entity.ExamResult;
import com.spring.Examination.entity.ExamUser;
import com.spring.Examination.entity.Question;
import com.spring.Examination.entity.User;
import com.spring.Examination.service.ExamResultService;
import com.spring.Examination.service.ExamService;
import com.spring.Examination.service.ExamUserService;
import com.spring.Examination.service.QuestionService;

@Controller
@RequestMapping("examQuestion")
public class ExamQuestionController {

	@Autowired
	private ExamService examService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private ExamResultService examResultService;

	@Autowired
	private ExamUserService examUserService;

	/*
	 * Display questions for selected examId
	 */
	@GetMapping("/{id}")
	public String examQuestion(@PathVariable int id, Model model) {
		Exam exam = examService.getExamById(id);
		model.addAttribute("exam", exam);
		model.addAttribute("questions", exam.getQuestions());
		return "examQuestions";
	}

	/*
	 * Create questions for selected examId
	 */
	@GetMapping("/create/{id}")
	public String createQuestion(@PathVariable("id") int examId, Model model) {
		model.addAttribute("examId", examId);
		model.addAttribute("question", new Question());
		return "createUpdateQuestion";
	}

	/*
	 * Retrieve question detail for selected examId and questionId
	 */
	@GetMapping("/edit/{examId}/{questionId}")
	public String editQuestion(@PathVariable("examId") int examId, @PathVariable("questionId") int questionId,
			Model model) {
		Question question = questionService.getQuestion(questionId);
		question.setAnswer1(question.getAnswers().get(0).getAnswer_description());
		question.setAnswer2(question.getAnswers().get(1).getAnswer_description());
		question.setAnswer3(question.getAnswers().get(2).getAnswer_description());
		question.setAnswer4(question.getAnswers().get(3).getAnswer_description());
		model.addAttribute("examId", examId);
		model.addAttribute("question", question);
		return "createUpdateQuestion";
	}

	/*
	 * Delete question for selected examId and questionId
	 */
	@GetMapping("/delete/{examId}/{questionId}")
	public String deleteQuestion(@PathVariable("examId") int examId, @PathVariable("questionId") int questionId,
			Model model) {
		Exam exam = examService.getExamById(examId);
		Question question = questionService.getQuestion(questionId);
		exam.getQuestions().remove(question);
		examService.save(exam);
		questionService.deleteQuestion(question);
		return "redirect:/examQuestion/" + examId;
	}

	/*
	 * Save question detail for selected examId and questionId
	 */
	@PostMapping("/save")
	public String saveOrupdate(@ModelAttribute Question newQuestion, HttpServletRequest request) {
		String examId = request.getParameter("examId");
		if (newQuestion.getId() == 0) {
			Exam exam = examService.getExamById(Integer.parseInt(examId));
			newQuestion.getAnswers().add(new Answer(newQuestion.getAnswer1()));
			newQuestion.getAnswers().add(new Answer(newQuestion.getAnswer2()));
			newQuestion.getAnswers().add(new Answer(newQuestion.getAnswer3()));
			newQuestion.getAnswers().add(new Answer(newQuestion.getAnswer4()));
			newQuestion.setCreatedDateTime(new Date());
			newQuestion.setUpdatedDateTime(new Date());
			questionService.save(newQuestion);
			exam.getQuestions().add(newQuestion);
			examService.save(exam);
		} else {
			Question oldQuestion = questionService.getQuestion(newQuestion.getId());
			oldQuestion.setQuestion_description(newQuestion.getQuestion_description());
			oldQuestion.setCorrect_answer(newQuestion.getCorrect_answer());
			oldQuestion.setPay_mark(newQuestion.getPay_mark());
			oldQuestion.getAnswers().get(0).setAnswer_description(newQuestion.getAnswer1());
			oldQuestion.getAnswers().get(1).setAnswer_description(newQuestion.getAnswer2());
			oldQuestion.getAnswers().get(2).setAnswer_description(newQuestion.getAnswer3());
			oldQuestion.getAnswers().get(3).setAnswer_description(newQuestion.getAnswer4());
			oldQuestion.setUpdatedDateTime(new Date());
			questionService.save(oldQuestion);
		}
		request.getSession().setAttribute("message", "Successfully Added");
		return "redirect:/examQuestion/" + examId;
	}

	/*
	 * Display selected exam questions for User
	 */
	@SuppressWarnings("deprecation")
	@GetMapping("/questionList/{examId}")
	public String question(@PathVariable("examId") int examId, Model model) {
		Date date = new Date();
		Exam exam = examService.getExamById(examId);
		int startTime = (Integer.parseInt(exam.getExamHour()) * 60) + Integer.parseInt(exam.getExamMinute());
		int currentTime = (date.getHours() * 60) + date.getMinutes();
		int spendTime = currentTime - startTime;
		int durationMins = exam.getDurationTime();
		int remainingMins = durationMins - spendTime;

		int hour = remainingMins / 60;
		int min = remainingMins % 60;
		List<Question> questions = exam.getQuestions();
		model.addAttribute("hour", hour);
		model.addAttribute("min", min - 1);
		model.addAttribute("sec", (60 - date.getSeconds()));
		model.addAttribute("exam", exam);
		model.addAttribute("questions", questions);
		return "questionList";
	}

	@PostMapping("/submitAnswer")
	public String check(HttpServletRequest request) {
		String[] questionIds = request.getParameterValues("questionId");
		int mark = 0;
		if (questionIds.length > 0) {
			for (String qId : questionIds) {
				if (null != request.getParameter("answerForQuestionId_" + qId)) {
					Question question = questionService.getQuestion(Integer.parseInt(qId));
					String selectedAnswer = request.getParameter("answerForQuestionId_" + qId).toString();
					String correctAnswer = question.getCorrect_answer();
					if (selectedAnswer.equals(correctAnswer)) {
						mark += question.getPay_mark();
					}
				}
			}
		}

		int examId = Integer.parseInt(request.getParameter("examId"));
		Exam exam = examService.getExamById(examId);

		User user = (User) request.getSession().getAttribute("loginUser");
		int userId = user.getId();
		List<ExamUser> examUserList = examUserService.getExamUserByUserId(userId);
		for (ExamUser examUser : examUserList) {
			if (examUser.getExamId() == examId) {
				examUser.setAnsweredStatus("1");
				examUserService.save(examUser);
			}
		}
		String passStatus = "Fail";
		if (mark >= exam.getPassMark()) {
			passStatus = "Pass";
		}
		examResultService.save(new ExamResult(examId, userId, user.getUsername(), exam.getExamName(),
				exam.getExamDate(),exam.getExamHour(),exam.getExamMinute(), exam.getDurationTime(), exam.getPassMark(), mark, passStatus));
		
		return "redirect:/user/";
	}

}
