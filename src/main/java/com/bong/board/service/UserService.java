package com.bong.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.board.domain.dto.ResponseDto;
import com.bong.board.domain.dto.UserDto;
import com.bong.board.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public int signupUser(UserDto userDto) {
		return userRepository.signupUser(userDto);
	}
	
	
	public ResponseDto<?> idOverlapCheck(UserDto userDto) {
	    String pass_yn = userRepository.idOverlapCheck(userDto);

	    if ("Y".equals(pass_yn)) {
	        int result = signupUser(userDto);
	        return result > 0 ? ResponseDto.createSuccess(userDto, "회원가입이 완료되었습니다.") : ResponseDto.createError("회원가입에 실패했습니다.");
        } else {
            return ResponseDto.createError("아이디가 이미 존재합니다.");
	    }
	}

	
	public ResponseDto<?> selectUser(UserDto userDto, HttpSession session) {
		
		try {
			UserDto uDto = userRepository.selectUser(userDto);
			
			if (uDto == null || uDto.getUserId().isEmpty()) {
	            return ResponseDto.createError("존재하지 않는 아이디입니다.");
			} else if (uDto.getUserPw().equals(userDto.getUserPw())) {
				session.setAttribute("userId", uDto.getUserId()); 
		        session.setAttribute("userNo", uDto.getUserNo());
	            return ResponseDto.createSuccess(userDto, "로그인 되었습니다.");
			} else {
	            return ResponseDto.createError("비밀번호가 틀렸습니다.");
			}
			
		} catch (Exception e) {
			return ResponseDto.createError("시스템 오류가 발생했습니다.");
		}
	}
	
}
