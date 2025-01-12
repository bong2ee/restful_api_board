package com.bong.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bong.board.domain.dto.BoardDto;
import com.bong.board.domain.dto.ListResultDto;
import com.bong.board.domain.dto.ResponseDto;
import com.bong.board.repository.BoardRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private HttpSession session;
	 
	/* 
	 * 게시판 리스트 
	 * 
	 * @param searchDto 검색조건 
	 * @return ListResultDto
	 * */
	public ListResultDto<BoardDto> selectBoardList(BoardDto searchDto) {

		searchDto.setDepth(1);
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
		
		String memberId = (String) session.getAttribute("memberId");
		Integer memberNo = (Integer) session.getAttribute("memberNo");
		boardDto.setMemberId(memberId);
		boardDto.setMemberNo(memberNo);
		
		// 대댓글 작성
		if (boardDto.getStep() > 0) { 
			
			boardDto.setDepth(3); //DEPTH -> 3
			boardDto.setTitle("ReComment");
			boardDto.setDelYn("N");	
			
		} else { 
			
			// 본문 작성
			if (boardDto.getDepth() == 0) {  
			
			int groupMax = boardRepository.selectGroupMax();
			boardDto.setGroupNo(groupMax+ 1);
			boardDto.setDepth(boardDto.getDepth() + 1); //DEPTH -> 1
			boardDto.setDelYn("N");
				
			} else {
			
			// 댓글 작성
			BoardDto searchDto = new BoardDto();
			searchDto.setGroupNo(boardDto.getGroupNo());
			int stepMax = boardRepository.selectStepMax(searchDto);
			boardDto.setStep(stepMax + 1);
			boardDto.setDepth(boardDto.getDepth() + 1); //DEPTH -> 2
			boardDto.setTitle("comment");
			boardDto.setDelYn("N");
			
			}
		} 
		
		int result = boardRepository.saveBoard(boardDto);
        return result > 0 ? ResponseDto.createSuccess(boardDto, "등록되었습니다.") : ResponseDto.createError("등록에 실패했습니다.");
	
	}

	/* 
	 * 게시글 상세 조회
	 * 
	 * @param searchDto 게시물 번호
	 * @return ResponseDto
	 * */
	public ResponseDto<?> selectBoardDetail(BoardDto searchDto) {
		
		searchDto.setDepth(1);
		System.out.println(searchDto);
		boardRepository.updateViewCnt(searchDto);
		BoardDto boardDto = boardRepository.selectBoardDetail(searchDto);
		
		return ResponseDto.createSuccess(boardDto, null);
	}

	/* 
	 * 게시글 상세 댓글 & 대댓글 목록 조회
	 * 
	 * @param searchDto 게시물 본문 번호, 댓글 번호
	 * @return ListResultDto
	 * */
	public ListResultDto<BoardDto> selectCommentList(BoardDto searchDto) {
		
		searchDto.setDepth(2);
		if (searchDto.getStep() > 0) { //대댓글 불러오기
			searchDto.setDepth(3);
		}
		List<BoardDto> dtoList = boardRepository.selectCommentList(searchDto);
		
		return ListResultDto.<BoardDto>builder()
									.data(dtoList)
									.build();
	}
	
	

}
