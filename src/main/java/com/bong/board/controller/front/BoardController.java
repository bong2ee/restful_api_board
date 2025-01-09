package com.bong.board.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {

	@GetMapping("/list")
	public String boardList(HttpSession session, Model model) {
		
		String memberId = (String) session.getAttribute("memberId");
		model.addAttribute("memberId", memberId);
		
		return "board/list";
	}
	
	

}
