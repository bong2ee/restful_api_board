package com.bong.board.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bong.board.domain.dto.BoardDto;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private HttpSession session;
	
	@GetMapping("/list")
	public String boardList(Model model) {
		
		String memberId = (String) session.getAttribute("memberId");
		model.addAttribute("memberId", memberId);
		
		return "board/list";
	}
	
	@GetMapping("/write")
	public String boardWrite(Model model) {
		
		String memberId = (String) session.getAttribute("memberId");
		Integer memberNo = (Integer) session.getAttribute("memberNo");
		model.addAttribute("memberId", memberId);
		model.addAttribute("memberNo", memberNo);
	    
		return "board/write";
	}
	
	@GetMapping("/detail")
	public String boardDetail(BoardDto boardDto, Model model) {
		
		String memberId = (String) session.getAttribute("memberId");
		Integer memberNo = (Integer) session.getAttribute("memberNo");
		model.addAttribute("memberId", memberId);
		model.addAttribute("memberNo", memberNo);
		model.addAttribute("boardDto", boardDto);
		
		return "board/detail";
	}
	
	

}
