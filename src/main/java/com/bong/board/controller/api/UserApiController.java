package com.bong.board.controller.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bong.board.domain.dto.ResponseDto;
import com.bong.board.domain.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {
	
	@PostMapping(value = "/signup", produces = "application/json")
	@ResponseBody
	public ResponseDto<?> save (@RequestBody UserDto userDto) {
		System.out.println(userDto);
		
		//userService. 
		return ResponseDto.createSuccess(userDto, "성공"); 
	}
//	@PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
//    public ResponseEntity postExample(@RequestBody UserDto request) {
//        // 요청 데이터 확인
//        System.out.println("Received request: " + request);
//
//        // 응답 생성
//        ResponseDto response = new ResponseDto("Data processed successfully", 200);
//        return ResponseEntity.ok(response);
//    }
}
