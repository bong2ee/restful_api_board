package com.bong.board.domain.dto;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@ToString
public class PageDto {
	
	private Integer totalCount; //총 데이터 수 
	private Integer pageIndex; 	//현재 페이지 번호
	private Integer pageSize; 	//한 페이지 당 행 갯수
	private Integer rn; 		//출력 번호

	//페이징 시작 행 번호
	@JsonIgnore
	public Integer getStartRow() {
		return ((this.pageIndex - 1) * this.pageSize) + 1;
	}

	//페이징 마지막 행 번호
	@JsonIgnore
	public Integer getEndRow() {
		return (getStartRow() + this.pageSize) - 1;
	
	}

}