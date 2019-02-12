package edu.spring.myboard.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import edu.spring.myboard.domain.Board;
import edu.spring.myboard.domain.File;
import edu.spring.myboard.service.BoardService;
import edu.spring.myboard.service.FileUploadService;

@RestController
@RequestMapping(value = "boardRest")
public class BoardRestController {
	
	@Autowired private FileUploadService fileService;
	@Autowired private BoardService boardService;
	
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public ResponseEntity<Integer> updateReply(MultipartHttpServletRequest req) {

		String title = req.getParameter("title");
		String writer = req.getParameter("name");
		String content = req.getParameter("content");
		
		List<MultipartFile> mf = req.getFiles("uploadfile");
		ArrayList<File> fileList = new ArrayList<File>();
		
		for(MultipartFile m : mf) {		
			String path = fileService.restore(m);
			File f = new File();
			f.setFileName(m.getOriginalFilename());
			f.setFilePath(path);
			
			try {
				f.setFileSize(m.getBytes().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			fileList.add(f);
		}
		
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		
		int result = boardService.insert(board, fileList);
		
		ResponseEntity<Integer> entity = null;
		if (result == 1) {
			entity = new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			entity = new ResponseEntity<Integer>(result, HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
