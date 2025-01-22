package com.bong.board.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bong.board.domain.dto.BoardDto;

@Mapper
public interface BoardRepository {

	int saveBoard(BoardDto boardDto);
	
	public List<BoardDto> selectBoardList(BoardDto searchDto);
	
	public int selectBoardCount(BoardDto searchDto);

	public int selectGroupMax();
	
	public int selectStepMax(BoardDto searchDto);
	
	public BoardDto selectBoardDetail(BoardDto searchDto);

	public List<BoardDto> selectCommentList(BoardDto searchDto);

	public void updateViewCnt(BoardDto searchDto);

	int updateBoard(BoardDto boardDto);

	
}
