package com.bong.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.board.domain.dto.BoardDto;
import com.bong.board.domain.dto.ListResultDto;
import com.bong.board.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	/* 
	 * 게시판 리스트 
	 * 
	 * @param searchDto 검색조건 
	 * @return ListResultDto
	 * */
	public ListResultDto<BoardDto> selectBoardList(BoardDto searchDto) {

		int totCnt = boardRepository.selectBoardCount(searchDto);
		searchDto.setTotalCount(totCnt);
		
		List<BoardDto> dtoList = boardRepository.selectBoardList(searchDto);

		return ListResultDto.<BoardDto>builder()
									.itemsCount(totCnt)
									.data(dtoList)
									.build();
	}
	
	

}
