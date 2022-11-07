package com.spring.Examination.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.Examination.entity.Exam;
import com.spring.Examination.entity.User;
import com.spring.Examination.service.ExamService;
import com.spring.Examination.service.UserService;

@Controller
@RequestMapping("exam")
public class ExamController {

	@Autowired
	private ExamService examService;

	@Autowired
	private UserService userService;

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("exam", new Exam());
		return "examCreateUpdate";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute Exam newExam, HttpServletRequest request) throws Exception {
		if (newExam.getId() == 0) {
			newExam.setCreatedDateTime(new Date());
			newExam.setUpdatedDateTime(new Date());
			examService.save(newExam);
			request.getSession().setAttribute("message", "Successfully Created !");
		} else {
			Exam oldExam = examService.getExamById(newExam.getId());
			oldExam.setExamName(newExam.getExamName());
			oldExam.setExamDate(newExam.getExamDate());
			oldExam.setExamHour(newExam.getExamHour());
			oldExam.setExamMinute(newExam.getExamMinute());
			oldExam.setDurationTime(newExam.getDurationTime());
			oldExam.setPassMark(newExam.getPassMark());
			oldExam.setUpdatedDateTime(new Date());
			examService.save(oldExam);
			request.getSession().setAttribute("message", "Successfully Updated !");
		}
		return "redirect:/exam/examList";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int examId, HttpSession session) throws Exception {
		Exam exam = examService.getExamById(examId);
		examService.delete(exam);
		session.setAttribute("message", "Successfully Deleted !");
		return "redirect:/exam/examList";
	}

	@GetMapping("/examList")
	public String examlist(Model model) {
		List<Exam> examList = examService.getExamList();
		model.addAttribute("exam", new Exam());
		model.addAttribute("examList", examList);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		model.addAttribute("todayDate", dateFormat.format(new Date()));
		return "examList";
	}

	@PostMapping("/search")
	public String exam(@ModelAttribute("exam") Exam exam, Model model) {

		List<Exam> examList = examService.getExamByConditions(exam.getExamName(), exam.getExamDate());
		model.addAttribute("examList", examList);
		return "examList";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		Exam exam = examService.getExamById(id);
		model.addAttribute("exam", exam);
		return "examCreateUpdate";
	}

	@GetMapping("/addUsersForExam/{examId}")
	public String addUsersForExam(@PathVariable int examId, Model model) {

		Exam exam = examService.getExamById(examId);
		List<User> userList = userService.getAllUsers();
		if (exam.getUsers().size() > 0) {
			userList.removeAll(exam.getUsers());
		}
		model.addAttribute("exam", exam);
		model.addAttribute("userList", userList);
		model.addAttribute("addedUsers", exam.getUsers());
		return "examUserAssign";
	}

	@PostMapping("/addExaminees")
	public String addExaminees(HttpServletRequest request, Model model) {

		String examId = request.getParameter("examId");
		String[] selectedAddUserIds = request.getParameterValues("selectedAddUserIds");

		Exam exam = examService.getExamById(Integer.parseInt(examId));
		for (String userId : selectedAddUserIds) {
			User selectedUser = userService.getUserById(Integer.parseInt(userId));
			selectedUser.getExams().add(exam);
			exam.getUsers().add(selectedUser);
		}
		examService.save(exam);
		request.getSession().setAttribute("message", "Successfully Added !");

		return "redirect:/exam/addUsersForExam/" + examId;
	}

	@PostMapping("/removeExaminees")
	public String removeExaminees(HttpServletRequest request, Model model) {

		String examId = request.getParameter("examId");
		String[] selectedRemoveUserIds = request.getParameterValues("selectedRemoveUserIds");

		Exam exam = examService.getExamById(Integer.parseInt(examId));
		for (String userId : selectedRemoveUserIds) {
			User selectedUser = userService.getUserById(Integer.parseInt(userId));
			selectedUser.getExams().add(exam);
			exam.getUsers().remove(selectedUser);
		}
		examService.save(exam);
		request.getSession().setAttribute("message", "Successfully Removed !");

		return "redirect:/exam/addUsersForExam/" + examId;
	}
}
