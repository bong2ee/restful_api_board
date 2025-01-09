package com.bong.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.bong.board.domain.dto.BoardDto;
import com.bong.board.repository.BoardRepository;

@SpringBootTest
@ActiveProfiles("test")
class BoardTests {

	@Autowired
	BoardRepository boardRepository;
	
//	@Test
//	void saveBoard() {
//		
//		
//		boardRepository.saveBoard(
//				BoardDto.builder()
//						.groupNo(1)
//						.step(0)
//						.depth(1)
//						.title("테스트제목입니다.")
//						.content("테스트내용입니다.")
//						.memberNo(1)
//						.memberId("bong")
//						.delYn("N")
//						.build()
//				);
//		
//	}
//	

//	@Test
//	void saveBoard2() {
//		
//		for(int i=1; i<=20; i++) {
//		
//			boardRepository.saveBoard(
//					BoardDto.builder()
//							.groupNo(i)
//							.step(0)
//							.depth(1)
//							.title(i+"테스트제목입니다.")
//							.content(i+"테스트내용입니다.")
//							.memberNo(1)
//							.memberId("bong")
//							.delYn("N")
//							.build()
//					);
//
//		}
//	}

//	@Test
//	void dfag () {
//		System.out.println("---------------------------------------------------------");
//	}
//
	@Test
	void selectBoard () {
		BoardDto searchDto = new BoardDto();
		
		int totalCnt =  boardRepository.selectBoardCount(searchDto);
		searchDto.setTotalCount(totalCnt);
		searchDto.setPageIndex(2);
		searchDto.setPageSize(5);

		List<BoardDto> boardList = boardRepository.selectBoardList(searchDto);
		
//		for (BoardDto b : boardList) {
//			System.out.println(b.getRn());
//		}

		//System.out.println(boardList);
	}
}
