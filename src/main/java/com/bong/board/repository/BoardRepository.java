package com.bong.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bong.board.domain.dto.BoardDto;

@Mapper
public interface BoardRepository {

	public int saveBoard(BoardDto boardDto);
	
	public List<BoardDto> selectBoardList(BoardDto searchDto);
	
	public int selectBoardCount(BoardDto searchDto);
	
}
