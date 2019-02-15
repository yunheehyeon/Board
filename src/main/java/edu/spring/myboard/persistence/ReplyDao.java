package edu.spring.myboard.persistence;

import java.util.List;

import edu.spring.myboard.domain.Reply;

public interface ReplyDao {

	List<Reply> selectByBno(int bno);
	int createReply(Reply reply);
	int deleteReply(int rno);
	
}
