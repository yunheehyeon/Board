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
import edu.spring.myboard.domain.File;
import edu.spring.myboard.service.BoardService;
import edu.spring.myboard.service.FileService;


@Controller
@RequestMapping(value = "board")
public class BoardController {

	@Autowired private BoardService boardService;
	@Autowired private FileService fileService;
		
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
	public String boardDtail(int bno, Model model, @RequestParam(defaultValue="1") int curPage) {
		
		int listCnt = boardService.selectBoardListCnt();  
	    Pagination pagination = new Pagination(listCnt, curPage);
	    model.addAttribute("pagination", pagination);
		
		Board item = boardService.selectBno(bno);
		model.addAttribute("item", item);
		
		List<File> fileList = fileService.selectFileByBno(bno);
		for(File f : fileList) {
			System.out.println("파일 정보 확인:" + f.toString());			
		}
		
		Gson gson = new Gson();
		String fileJson = gson.toJson(fileList);
		model.addAttribute("fileList", fileJson);
		
		return "boardDetail";
	}
	
	@RequestMapping(value = "regist", method = RequestMethod.GET)
	public String registBoard() {
		return "registBoard";
	}
		
}
