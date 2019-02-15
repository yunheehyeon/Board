package edu.spring.myboard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import edu.spring.myboard.domain.Board;
import edu.spring.myboard.domain.File;
import edu.spring.myboard.domain.Reply;
import edu.spring.myboard.service.ReplyService;

@RestController
@RequestMapping(value = "replyRest")
public class ReplyRestController {

	@Autowired ReplyService replyService;
	
	@RequestMapping(value = "insert", method = RequestMethod.POST)
	public ResponseEntity<Integer> updateReply(@RequestBody Reply reply) {
		
		System.out.println(reply);
		
		int result = replyService.createReply(reply);
		
		ResponseEntity<Integer> entity = null;
		if (result == 1) {
			entity = new ResponseEntity<Integer>(result, HttpStatus.OK);
		} else {
			entity = new ResponseEntity<Integer>(result, HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value = "selectAll/{bno}", method = RequestMethod.GET)
	public ResponseEntity<List<Reply>> selectReplyBybno(@PathVariable(name="bno") int bno) {

		List<Reply> result = replyService.selectByBno(bno);
		
		ResponseEntity<List<Reply>> entity = null;
		if (result != null) {
			entity = new ResponseEntity<List<Reply>>(result, HttpStatus.OK);
		} else {
			entity = new ResponseEntity<List<Reply>>(result, HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
