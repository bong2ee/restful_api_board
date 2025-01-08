package com.bong.board.domain.dto;

import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {

	private String memberNm;
	private String email;
	private int memberNo;
	private String memberId;
	private String password;
	private String regDate;
	
}
