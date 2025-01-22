package com.bong.board.controller.api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	 * 게시글 상세 조회
	 * 
	 * @param searchDto 게시물 번호
	 * @return ResponseDto
	 * */
	@GetMapping("/{boardNo}")
	public ResponseDto<?>  selectBoardDetail(@PathVariable("boardNo") int boardNo) {
		return boardService.selectBoardDetail(boardNo);
	}
	
	/* 
	 * 게시글 본문 저장  
	 * 
	 * @param boardDto 작성내용
	 * @return ResponseDto
	 * */
	@PostMapping(value = "/save", produces = "application/json")
	@ResponseBody
	public ResponseDto<?> saveboard (@ModelAttribute BoardDto boardDto, @RequestParam(value = "file", required = false) MultipartFile file) {
		return boardService.saveBoard(boardDto, file);
	}
	
	/* 
	 * 게시글 댓글 & 대댓글 저장  
	 * 
	 * @param boardDto 작성내용
	 * @return ResponseDto
	 * */
	@PostMapping(value = "/save-comment", produces = "application/json")
	@ResponseBody
	public ResponseDto<?> saveComment (@RequestBody BoardDto boardDto) {
		return boardService.saveBoard(boardDto, null);
	}
	
	/* 
	 * 게시글 본문 수정
	 * 
	 * @param boardDto 작성내용
	 * @return ResponseDto
	 * */
	@PutMapping("/{boardNo}")
	@ResponseBody
	public ResponseDto<?> updateBoard(@PathVariable("boardNo") int boardNo,
										@ModelAttribute BoardDto boardDto,
										@RequestParam(value = "file", required = false) MultipartFile file) {
	    boardDto.setUpdateMode("update");
	    return boardService.updateBoard(boardDto, file);
	}

		
	/* 
	 * 게시글 본문 & 댓글 & 대댓글 삭제 
	 * 
	 * @param boardDto
	 * @return ResponseDto
	 * */
	@DeleteMapping("/{boardNo}")
	public ResponseDto<?>  deleteBoard(@PathVariable("boardNo") int boardNo) {
		BoardDto boardDto = new BoardDto();
		boardDto.setBoardNo(boardNo);
		boardDto.setUpdateMode("delete");
		return boardService.updateBoard(boardDto, null);
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
	
	/*
	 * 게시판 Summernote 이미지 파일 저장
	 * 
	 * @param file 파일
	 * return jsonObject
	 * */
	@PostMapping(value="/saveSummernoteImg", produces = "application/json")
	public @ResponseBody String saveSummernoteImg(@RequestParam("file") MultipartFile file) {
		JsonObject jsonObject = boardService.saveSummernoteImg(file);
		return new Gson().toJson(jsonObject);
	}
}
