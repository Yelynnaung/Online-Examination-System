package com.spring.Examination.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.Examination.entity.Exam;
import com.spring.Examination.entity.ExamResult;
import com.spring.Examination.service.ExamResultService;

@Controller
@RequestMapping("/examResult")
public class ExamResultController {

	@Autowired
	private ExamResultService examResultService;

	@GetMapping("/")
	public String examResult(Model model) {
		List<ExamResult> examResultList = examResultService.getExamResultList();
		model.addAttribute("exam", new Exam());
		model.addAttribute("examResultList", examResultList);
		return "examResults";
	}

	@GetMapping("/viewResult/{examId}/{examDate}/{hour}/{min}")
	private String viewResult(@PathVariable("examId") int examId, @PathVariable("examDate") String date,
			@PathVariable("hour") int hour, @PathVariable("min") int min, Model model) {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		@SuppressWarnings("deprecation")
		String examDate = dateFormat.format(new Date(date)).toString();
		List<ExamResult> examResultList = examResultService.getExamResultListByExamIdAndExamDateAndHourAndMin(examId,
				examDate, Integer.toString(hour), Integer.toString(min));

		model.addAttribute("examResultList", examResultList);
		return "examResult";
	}
	
	@PostMapping("/search")
	public String exam(@ModelAttribute("exam") Exam exam, Model model) {		
		List<ExamResult> examResultList = examResultService.getExamResultByConditions(exam.getExamName(),exam.getExamDate());
		model.addAttribute("examResultList", examResultList);
		return "examResults";
	}

}
