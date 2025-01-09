package com.bong.board.domain.dto;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ListResultDto<T> {
	
    private int itemsCount;	//list 총 갯수 
    private List<T> data;
    
}
