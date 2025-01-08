package com.bong.board.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@GetMapping("/signin")
	public String signin() {
		return "user/signin";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "user/signup";
	}
	
	@GetMapping("/signout")
	public String logout(HttpSession session, Model model) {
		
		String userId = (String) session.getAttribute("userId");
		session.invalidate();
		int logout = 1;
		model.addAttribute("logout", logout);

		return "home";
	}

}
