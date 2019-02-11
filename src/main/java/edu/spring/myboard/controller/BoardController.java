package edu.spring.myboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "board")
public class BoardController {

	@RequestMapping(method = RequestMethod.GET)
	public String displayBoard() {
		return "board";
	}
	
	
	
}
