package com.bong.board.controller.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.multipart.MultipartFile;
import com.bong.board.domain.dto.BoardDto;
import com.bong.board.domain.dto.ListResultDto;
import com.bong.board.domain.dto.ResponseDto;
import com.bong.board.service.BoardService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.http.HttpSession;
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
	@PostMapping(value = "/save", produces = "application/json")
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
	
	
	/* 
	 * 게시글 상세 댓글 & 대댓글 목록 조회
	 * 
	 * @param searchDto 게시물 본문 번호, 댓글 번호
	 * @return ListResultDto
	 * */
	@GetMapping("/commentList")
	public ListResultDto<BoardDto> selectCommentList(BoardDto searchDto) {
		return boardService.selectCommentList(searchDto);
	}
	
	
	@PostMapping(value="/saveSummernoteImg", produces = "application/json")
	public @ResponseBody String saveSummernoteImg(@RequestParam("file") MultipartFile file) {
		JsonObject jsonObject = boardService.saveSummernoteImg(file);
		return new Gson().toJson(jsonObject);
	}
}
