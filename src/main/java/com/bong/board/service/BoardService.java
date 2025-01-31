package com.bong.board.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bong.board.domain.dto.BoardDto;
import com.bong.board.domain.dto.ListResultDto;
import com.bong.board.domain.dto.ResponseDto;
import com.bong.board.repository.BoardRepository;
import com.google.gson.JsonObject;

import jakarta.servlet.http.HttpSession;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private FileService fileService;

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
	 * 게시글 본문&댓글&대댓글 저장  
	 * 
	 * @param boardDto 작성내용
	 * @return ResponseDto
	 * */
	public ResponseDto<?> saveBoard(BoardDto boardDto, MultipartFile file) {	
		
		int result = 0;
		
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
			
			
			if (boardDto.getDepth() == 0) {  
			
				// 본문 작성
				int groupMax = boardRepository.selectGroupMax();
				boardDto.setGroupNo(groupMax+ 1);
				boardDto.setDepth(boardDto.getDepth() + 1); //DEPTH -> 1
				boardDto.setDelYn("N");
				
		
				//파일 업로드
				if(!file.isEmpty()){
					fileService.store(file, boardDto);
				}
				
			} else {
			
				// 댓글 작성
				BoardDto searchDto = new BoardDto();
				searchDto.setGroupNo(boardDto.getGroupNo());
				int stepMax = boardRepository.selectStepMax(searchDto);
				boardDto.setStep(stepMax + 1);
				boardDto.setDepth(boardDto.getDepth() + 1); //DEPTH -> 2
				boardDto.setTitle("comment");
				boardDto.setDelYn("N");
				boardDto.setFileName("");
				boardDto.setFilePath("");
			
			}
		} 
			
		try {
			boardRepository.saveBoard(boardDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.createError("등록에 실패했습니다.");
		}
		return ResponseDto.createSuccess(boardDto, "등록되었습니다.");
	}
	
	/* 
	 * 댓글 삭제 , 게시글 수정 & 삭제
	 * 
	 * @param searchDto 게시물 번호
	 * @return ResponseDto
	 * */
	public ResponseDto<?> updateBoard(BoardDto boardDto, MultipartFile file) {	
		
		if(!file.isEmpty()){
			System.out.println("파ㅣㄹ잇슴");
			//fileService.store(file, boardDto);
		}

		try {
			boardRepository.updateBoard(boardDto);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.createError("수정에 실패했습니다.");
		} 
		return ResponseDto.createSuccess(boardDto, "수정되었습니다.");
	}

	/* 
	 * 게시글 상세 조회
	 * 
	 * @param searchDto 게시물 번호
	 * @return ResponseDto
	 * */
	public ResponseDto<?> selectBoardDetail(int boardNo) {
		
		BoardDto boardDto = new BoardDto();
		BoardDto searchDto = new BoardDto();
		searchDto.setBoardNo(boardNo);
		searchDto.setDepth(1);
		try {

			boardRepository.updateViewCnt(searchDto);
			boardDto = boardRepository.selectBoardDetail(searchDto);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseDto.createError("조회에 실패하였습니다.");
		}
		
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
		
		dtoList.stream().forEach(c -> {
		    if ("Y".equals(c.getDelYn())) {
		        c.setContent("삭제된 댓글입니다.");
		    }
		});
		
		return ListResultDto.<BoardDto>builder()
									.data(dtoList)
									.build();
	}

	/*
	 * 게시판 Summernote 이미지 파일 저장
	 * 
	 * @param file 파일
	 * return jsonObject
	 * */
	public JsonObject saveSummernoteImg(MultipartFile file) {
		JsonObject jsonObject = new JsonObject();
		String fileRoot = "C:\\summernoteImg\\"; //파일이 저장될 경로 설정
		String originalFileName = file.getOriginalFilename(); //실제 파일명 
		String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); //확장자
		String saveFileName = UUID.randomUUID()+extension; //저정할 파일명 --> UUID+확장자
			
		File targetFile = new File(fileRoot+saveFileName);
		
		try {
			InputStream fileStream = file.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);
			jsonObject.addProperty("url", "/summernoteImg/"+saveFileName);
			jsonObject.addProperty("responseCode", "success");
		} catch(IOException e) {
			FileUtils.deleteQuietly(targetFile);
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}	
		
		return jsonObject;
	}

	
	

}
