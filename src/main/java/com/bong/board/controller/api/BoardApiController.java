package com.bong.board.controller.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bong.board.domain.dto.BoardDto;
import com.bong.board.domain.dto.ListResultDto;
import com.bong.board.domain.dto.ResponseDto;
import com.bong.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardApiController {
	
	@Autowired
	BoardService boardService;
	
	/* 
	 * 게시판 리스트 
	 * 
	 * @param searchDto 검색조건 
	 * @return ListResultDto
	 * */
	@GetMapping("/list")
	public ListResultDto<BoardDto> selectBoardList(BoardDto searchDto) {
		return boardService.selectBoardList(searchDto);
	}
	
	/* 
	 * 게시글 본문&댓글 저장  
	 * 
	 * @param boardDto 작성내용
	 * @return ResponseDto
	 * */
	@PostMapping(value = "/saveboard", produces = "application/json")
	@ResponseBody
	public ResponseDto<?> saveboard (@RequestBody BoardDto boardDto) {
		return boardService.saveBoard(boardDto);
		
	}
	
	/* 
	 * 게시글 상세 조회
	 * 
	 * @param searchDto 게시물 번호
	 * @return ResponseDto
	 * */
	@GetMapping("/detail")
	public ResponseDto<?>  selectBoardDetail(BoardDto searchDto) {
		return boardService.selectBoardDetail(searchDto);
	}
}
