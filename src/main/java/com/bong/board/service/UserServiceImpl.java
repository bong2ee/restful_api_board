package com.bong.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.board.domain.dto.UserDto;
import com.bong.board.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public int signupUser(UserDto userDto) {
		return userRepository.signupUser(userDto);
	}
	
	@Override
	public String idOverlapCheck(UserDto userDto) {
	    String pass_yn = userRepository.idOverlapCheck(userDto);
	    
	    if ("Y".equals(pass_yn)) {
	        int result = signupUser(userDto);
	        return result > 0 ? "SUCCESS" : "FAILURE";
        } else {
            return "DUPLICATE";
	    }
	}

	
	
	
}
