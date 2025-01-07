package com.bong.board.repository;

import org.apache.ibatis.annotations.Mapper;

import com.bong.board.domain.dto.UserDto;

@Mapper
public interface UserRepository {

	public int signupUser(UserDto userDto);

	public String idOverlapCheck(UserDto userDto);

	public UserDto selectUser(UserDto userDto);
	
}
