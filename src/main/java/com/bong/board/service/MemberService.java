package com.bong.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.board.domain.dto.ResponseDto;
import com.bong.board.domain.dto.MemberDto;
import com.bong.board.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	
	public ResponseDto<?> signup(MemberDto memberDto) {
		String pass_yn = memberRepository.checkIdOverlap(memberDto);

	    if ("Y".equals(pass_yn)) {
			int result = memberRepository.signup(memberDto);
	        return result > 0 ? ResponseDto.createSuccess(memberDto, "회원가입이 완료되었습니다.") : ResponseDto.createError("회원가입에 실패했습니다.");	        
        } else {
            return ResponseDto.createError("아이디가 이미 존재합니다.");
	    }
	}
		
	public ResponseDto<?> signin(MemberDto memberDto, HttpSession session) {
		
		try {
			MemberDto uDto = memberRepository.selectMember(memberDto);
			
			
			if (uDto == null || uDto.getMemberId().isEmpty()) {
	            return ResponseDto.createError("존재하지 않는 아이디입니다.");
			} else if (uDto.getPassword().equals(memberDto.getPassword())) {
				session.setAttribute("memberId", uDto.getMemberId()); 
		        session.setAttribute("memberNo", uDto.getMemberNo());
	            return ResponseDto.createSuccess(memberDto, "로그인 되었습니다.");
			} else {
	            return ResponseDto.createError("비밀번호가 틀렸습니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.createError("시스템 오류가 발생했습니다.");
		}
	}
	
}
