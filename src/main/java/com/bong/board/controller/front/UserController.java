package com.bong.board.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/signin")
	public String signin() {
		return "user/signin";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "user/signup";
	}
}
