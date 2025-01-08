package com.bong.board.repository;

import org.apache.ibatis.annotations.Mapper;

import com.bong.board.domain.dto.MemberDto;

@Mapper
public interface MemberRepository {

	public int signup(MemberDto memberDto);

	public String checkIdOverlap(MemberDto memberDto);

	public MemberDto selectMember(MemberDto memberDto);
	
}
