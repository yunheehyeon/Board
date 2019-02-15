package edu.spring.myboard.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import edu.spring.myboard.domain.Board;
import edu.spring.myboard.service.BoardService;


@Controller
@RequestMapping(value = "board")
public class BoardController {

	@Autowired private BoardService boardService;
		
	@RequestMapping(method = RequestMethod.GET)
	public String searchPage(Model model, @RequestParam(defaultValue="1") int curPage) {
		
		int listCnt = boardService.selectBoardListCnt();
	        
	    Pagination pagination = new Pagination(listCnt, curPage);
	        	    
	    model.addAttribute("pagination", pagination);
		
		List<Board> result = boardService.selectPage(curPage-1);
		
		Gson gson = new Gson();
		
		String jsonResult = gson.toJson(result);
		
		model.addAttribute("boardList", jsonResult);		
		
		return  "board";
		
	}
	
	@RequestMapping(value = "boardDetail", method = RequestMethod.GET)
	public String boardDtail(int bno, Model model) {
		
		Board item = boardService.selectBno(bno);
		model.addAttribute("item", item);
		
		return "boardDetail";
	}
	
	@RequestMapping(value = "regist", method = RequestMethod.GET)
	public String registBoard() {
		return "registBoard";
	}
		
	
	
}
