package com.bong.board.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model model, HttpSession session) {
		
		String userId = (String) session.getAttribute("userId");
		model.addAttribute("userId", userId);

		return "home";
	}
}
