package com.bong.board.service;

import com.bong.board.domain.dto.UserDto;

public interface UserService {

	public int signupUser(UserDto userDto);
	
	public String idOverlapCheck(UserDto userDto);
	
}
