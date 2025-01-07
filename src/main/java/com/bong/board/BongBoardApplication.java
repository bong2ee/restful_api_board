package com.bong.board;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bong.board.repository")
public class BongBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BongBoardApplication.class, args);
	}

}
