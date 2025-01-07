package com.bong.board.repository;

import org.apache.ibatis.annotations.Mapper;

import com.bong.board.domain.dto.UserDto;

@Mapper
public interface UserRepository {

	int signupUser(UserDto userDto);

	String idOverlapCheck(UserDto userDto);
	
}
