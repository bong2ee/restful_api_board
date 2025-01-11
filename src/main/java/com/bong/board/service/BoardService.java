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

	/* 
	 * 게시글 본문&댓글 저장  
	 * 
	 * @param boardDto 작성내용
	 * @return ResponseDto
	 * */
	
	public ResponseDto<?> saveBoard(BoardDto boardDto) {
		
		//System.out.println(boardDto);
		
		if(boardDto.getDepth() == 0) { // 본문 작성
			
			int groupMax = boardRepository.selectGroupMax();
			boardDto.setGroupNo(groupMax+ 1);
			boardDto.setDepth(boardDto.getDepth() + 1); //DEPTH -> 1
			boardDto.setDelYn("N");
			
			int result = boardRepository.saveBoard(boardDto);
	        return result > 0 ? ResponseDto.createSuccess(boardDto, "등록되었습니다.") : ResponseDto.createError("등록에 실패했습니다.");
		
		} else { // 댓글 작성
		
			int stepMax = boardRepository.selectStepMax(boardDto);
			boardDto.setStep(stepMax + 1);
			boardDto.setDepth(boardDto.getDepth() + 1); //DEPTH -> 2
			boardDto.setTitle("comment");
			boardDto.setDelYn("N");
		
			int result = boardRepository.saveBoard(boardDto);
	        return result > 0 ? ResponseDto.createSuccess(boardDto, "등록되었습니다.") : ResponseDto.createError("등록에 실패했습니다.");
		
		} 
			
		//return ResponseDto.createError("시스템 오류가 발생했습니다.");
	}

	/* 
	 * 게시글 상세 조회
	 * 
	 * @param searchDto 게시물 번호
	 * @return ResponseDto
	 * */
	public ResponseDto<?> selectBoardDetail(BoardDto searchDto) {
		
		BoardDto boardDto = boardRepository.selectBoardDetail(searchDto);
		
		return ResponseDto.createSuccess(boardDto, null);
	}

	/* 
	 * 게시글 상세 댓굴 목록 조회
	 * 
	 * @param searchDto 게시물 본문 번호
	 * @return ListResultDto
	 * */
	public ListResultDto<BoardDto> selectBoardDetailComment(BoardDto searchDto) {
		List<BoardDto> dtoList = boardRepository.selectBoardDetailComment(searchDto);
		return ListResultDto.<BoardDto>builder()
									.data(dtoList)
									.build();
	}
	
	

}
