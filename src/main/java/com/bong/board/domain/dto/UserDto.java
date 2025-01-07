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
public class UserDto {

	private String userNm;
	private String emailAddr;
	private String userNo;
	private String userId;
	private String userPw;
	private String regDate;
	
}
