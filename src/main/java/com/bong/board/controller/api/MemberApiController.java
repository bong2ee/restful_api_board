package com.bong.board.controller.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bong.board.domain.dto.ResponseDto;
import com.bong.board.domain.dto.MemberDto;
import com.bong.board.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberApiController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping(value = "/signup", produces = "application/json")
	@ResponseBody
	public ResponseDto<?> signup (@RequestBody MemberDto memberDto) {
		return memberService.signup(memberDto);
	}
	
	@PostMapping(value = "/signin", produces = "application/json")
	@ResponseBody
	public ResponseDto<?> signin (@RequestBody MemberDto memberDto, HttpSession session) {
		return memberService.signin(memberDto, session);
		
	}
	

}
