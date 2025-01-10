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
	
	@PostMapping(value = "/saveboard", produces = "application/json")
	@ResponseBody
	public ResponseDto<?> saveboard (@RequestBody BoardDto boardDto) {
		//System.out.println("데이터가 넘어오나요?"+boardDto);
		//return "o";
		return boardService.saveBoard(boardDto);
		
	}
}
