package com.bong.board.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {

	@GetMapping("/signin")
	public String signin(HttpSession session, Model model) {
		
		if(session != null) {
			String loginMessage = (String) session.getAttribute("loginMessage");
			if (loginMessage != null) {
	            model.addAttribute("loginMessage", loginMessage);
	            session.removeAttribute("loginMessage");
	        }
		}
		return "member/signin";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "member/signup";
	}
	
	@GetMapping("/signout")
	public String logout(HttpSession session, Model model) {
		
		//String memberId = (String) session.getAttribute("memberId");
		session.invalidate();
		int logout = 1;
		model.addAttribute("logout", logout);

		return "home";
	}

}
