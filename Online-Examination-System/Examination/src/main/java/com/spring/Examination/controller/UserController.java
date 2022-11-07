package com.spring.Examination.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.Examination.entity.Exam;
import com.spring.Examination.entity.ExamResult;
import com.spring.Examination.entity.ExamUser;
import com.spring.Examination.entity.User;
import com.spring.Examination.service.ExamResultService;
import com.spring.Examination.service.ExamUserService;
import com.spring.Examination.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "\\src\\images\\";

	@Autowired
	private UserService userService;

	@Autowired
	private ExamUserService examUserService;

	@Autowired
	private ExamResultService examResultService;

	@GetMapping("/")
	private String userHome() {
		return "userHome";
	}

	@GetMapping("/userExam")
	public String userExam(HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		prepareExamList(loginUser, model);
		return "userExam";
	}

	@GetMapping("/examResult")
	private String examResult(HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		List<ExamResult> examResultList = examResultService.getExamResultListByUserId(loginUser.getId());
		model.addAttribute("examResult", examResultList);
		return "userExamResult";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("roles", getRoles());
		model.addAttribute("user", new User());
		return "userCreateUpdate";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute User newUser, @RequestParam("image") MultipartFile inputFile,
			HttpSession session) throws Exception {
		if (newUser.getId() == 0) {
			newUser.setPhoto(inputFile.getOriginalFilename());
			newUser.setCreatedDateTime(new Date());
			newUser.setUpdatedDateTime(new Date());
			userService.save(newUser);
			uploadImage(inputFile);
			session.setAttribute("message", "User Successfully Created !");
		} else {
			User oldUser = userService.getUserById(newUser.getId());
			newUser.getExams().addAll(oldUser.getExams());
			newUser.setPhoto(inputFile.getOriginalFilename());
			newUser.setCreatedDateTime(oldUser.getCreatedDateTime());
			newUser.setUpdatedDateTime(new Date());
			userService.save(newUser);
			session.setAttribute("message", "User Successfully Updated !");
		}
		return "redirect:/user/userList";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int userId, HttpServletRequest request) throws Exception {
		deleteFromExamUser(userId);
		userService.deleteUserById(userId);
		request.getSession().setAttribute("message", "Successfully Deleted");
		return "redirect:/user/userList";
	}

	@PostMapping("/delete")
	public String deleteAll(HttpServletRequest request) {
		String[] selectedUserId = request.getParameterValues("selectedUserId");
		if (selectedUserId.length > 0) {
			for (String id : selectedUserId) {
				deleteFromExamUser(Integer.parseInt(id));
				userService.deleteUserById(Integer.parseInt(id));
			}
			request.getSession().setAttribute("message", "Successfully Deleted " + selectedUserId.length + " Users");
		}
		return "redirect:/user/userList";
	}

	@GetMapping("/userList")
	public String userList(Model model) {
		List<User> userList = userService.getAllUsers();
		model.addAttribute("user", new User());
		model.addAttribute("userList", userList);
		return "userList";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("roles", getRoles());
		model.addAttribute("user", user);
		return "userCreateUpdate";
	}

	@PostMapping("/search")
	public String search(@ModelAttribute User user, Model model) {
		List<User> searchUserList = userService.getUsersByConditions(user.getUsername(), user.getEmail());
		model.addAttribute("userList", searchUserList);
		return "userList";
	}

	@GetMapping("/detail/{id}")
	public String detail(@PathVariable int id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "userDetail";
	}

	private void uploadImage(MultipartFile inputFile) throws IOException {
		Path path = Paths.get(UPLOAD_DIRECTORY + inputFile.getOriginalFilename());
		new File(UPLOAD_DIRECTORY).mkdir();
		Files.write(path, inputFile.getBytes());
	}

	private void deleteFromExamUser(int userId) {
		List<ExamUser> examUsers = examUserService.getExamUserByUserId(userId);
		for (ExamUser examUser : examUsers) {
			examUserService.delete(examUser.getId());
		}
	}

	private void prepareExamList(User loginUser, Model model) {
		List<Exam> totalExamList = loginUser.getExams();
		List<ExamUser> examUserList = examUserService.getExamUserByUserId(loginUser.getId());
		List<Exam> alreadyAnswerdUser = new ArrayList<>();
		for (ExamUser examUser : examUserList) {
			if (examUser.getAnsweredStatus() != null) {
				for (Exam exam : totalExamList) {
					if (exam.getId() == examUser.getExamId()) {
						alreadyAnswerdUser.add(exam);
					}
				}
			}
		}
		totalExamList.removeAll(alreadyAnswerdUser);
		model.addAttribute("examList", totalExamList);
	}

	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		roles.add("Admin");
		roles.add("User");
		return roles;
	}

}
