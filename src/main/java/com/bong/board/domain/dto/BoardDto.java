package com.bong.board.domain.dto;

import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDto extends PageDto {

    private int boardNo;         // 게시글 번호
    private int groupNo;         // 본문 번호 (증조 할머니 번호)
    private int step;            // 최상위 댓글의 STEP (나의 할머니 번호)
    private int depth;           // 댓글 깊이 (할머니, 엄마, 딸 구분)
    private int viewCnt;	     // 조회수
    private String title;        // 제목
    private String content;      // 본문 (대용량 텍스트)
    private int memberNo;        // 작성자 번호
    private String memberId;     // 작성자 ID
    private String delYn;        // 삭제 여부
    private Date regDate;        // 작성 날짜
    private Date modDate;        // 수정 날짜
    
    //검색
    private String keyword;
    private String searchCate;
    
	
}
