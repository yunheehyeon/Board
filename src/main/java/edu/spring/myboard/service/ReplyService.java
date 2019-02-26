package edu.spring.myboard.service;

import java.util.List;

import edu.spring.myboard.domain.Reply;

public interface ReplyService {

	List<Reply> selectByBno(int bno);
	int createReply(Reply reply);
	int deleteReply(int rno);
}
