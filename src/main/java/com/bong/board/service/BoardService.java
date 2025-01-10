package com.bong.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.board.domain.dto.BoardDto;
import com.bong.board.domain.dto.ListResultDto;
import com.bong.board.domain.dto.ResponseDto;
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

	public ResponseDto<?> saveBoard(BoardDto boardDto) {
		
		System.out.println("여기오지?" + boardDto);
		
		if(boardDto.getDepth() == 0) {
			
			int groupMax = boardRepository.selectGroupMax();
			boardDto.setGroupNo(groupMax+ 1);
			boardDto.setDepth(1);
			boardDto.setDelYn("N");
			
			int result = boardRepository.saveBoard(boardDto);
			
			//return null;
	        return result > 0 ? ResponseDto.createSuccess(boardDto, "등록되었습니다.") : ResponseDto.createError("등록에 실패했습니다.");
		}
		
		return ResponseDto.createError("시스템 오류가 발생했습니다.");
	}
	
	

}
