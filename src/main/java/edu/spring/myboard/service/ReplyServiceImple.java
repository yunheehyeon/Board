package edu.spring.myboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.myboard.domain.Reply;
import edu.spring.myboard.persistence.ReplyDao;

@Service
public class ReplyServiceImple implements ReplyService {

	@Autowired private ReplyDao replyDao;
	
	public List<Reply> selectByBno(int bno) {
		return replyDao.selectByBno(bno);
	}

	public int createReply(Reply reply) {
		return replyDao.createReply(reply);
	}

	public int deleteReply(int rno) {
		return replyDao.deleteReply(rno);
	}

}
