package com.bong.board.domain.dto;

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
public class ResponseDto<T> {
	
	// 상태 구분1
    private static final String SUCCESS_STATUS = "success";
    private static final String FAIL_STATUS = "fail";
    private static final String ERROR_STATUS = "error";

    private String status;
    private T data;
    private String message;


    // 성공했을 때 data와 message 리턴
    public static <T> ResponseDto<T> createSuccess(T data, String message){
        return new ResponseDto<>(SUCCESS_STATUS, data, message);
    }

    // 성공했지만 리턴 데이터가 없는 경우 message 리턴
    public static <T> ResponseDto<T> createSuccessNoContent(String message){
        return new ResponseDto<>(SUCCESS_STATUS, null, message);
    }

    // 예외 발생으로 API 호출 실패시 반환
    public static ResponseDto<?> createError(String message) {
        return new ResponseDto<>(ERROR_STATUS, null, message);
    }

}
