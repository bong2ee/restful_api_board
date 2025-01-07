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
import com.bong.board.domain.dto.UserDto;
import com.bong.board.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/signup", produces = "application/json")
	@ResponseBody
	public ResponseDto<?> save (@RequestBody UserDto userDto) {
		String result = userService.idOverlapCheck(userDto);
	    if ("SUCCESS".equals(result)) {
	        return ResponseDto.createSuccess(userDto, "회원가입이 완료되었습니다.");
	    } else {
	        return ResponseDto.createError(result.equals("DUPLICATE") ? "아이디가 이미 존재합니다." : "회원가입에 실패했습니다.");
	    }
	}
	
	

}
