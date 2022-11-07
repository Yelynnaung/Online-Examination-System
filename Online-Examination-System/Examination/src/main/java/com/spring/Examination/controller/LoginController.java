package com.spring.Examination.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.Examination.entity.User;
import com.spring.Examination.service.UserService;

@Controller("/")
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping
	public String loginPage(Model model) {
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute User user, HttpSession session, Model model) {
		String username = user.getUsername();
		String password = user.getPassword();
		if (!username.equals("") && !password.equals("")) {
			User loginUser = userService.getLoginUser(username, password);
			if (loginUser != null) {
				session.setAttribute("loginUser", loginUser);
				if (loginUser.getRole().equals("Admin")) {
					return "redirect:/adminHome";
				} else {
					return "redirect:/user/";
				}
			}
		}
		session.setAttribute("message", "Invalid User !");
		return "redirect:/";
	}
	@GetMapping("/logout")
	private String logout(HttpSession session) {
		session.removeAttribute("loginUser");
		return "redirect:/";
	}

	@GetMapping("/adminHome")
	private String adminHome() {
		return "adminHome";
	}
}
