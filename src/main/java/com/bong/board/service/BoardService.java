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
	
	
	public ListResultDto<BoardDto> selectBoardList(BoardDto searchDto) {
	    List<BoardDto> dtoList = boardRepository.selectBoardList(searchDto);

	    ListResultDto<BoardDto> resultDto = 
    				ListResultDto.<BoardDto>builder()
						        .itemsCount(dtoList.size()) 
						        .data(dtoList) 
						        .build();

	    return resultDto;
	}
		
	
	
}
