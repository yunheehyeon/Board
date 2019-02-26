package edu.spring.myboard.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.myboard.domain.Reply;

@Repository
public class ReplyDaoImple implements ReplyDao {

	private static final String REPLY_MAPPER = "edu.spring.myboard.mappers.ReplyMapper";

	@Autowired private SqlSession session;

	public List<Reply> selectByBno(int bno) {
		Reply reply = new Reply();
		reply.setBno(bno);
		return session.selectList(REPLY_MAPPER+".selectByBno", reply);
	}

	public int createReply(Reply reply) {
		return session.insert(REPLY_MAPPER+".createReply", reply);
	}

	public int deleteReply(int rno) {
		Reply reply = new Reply();
		reply.setRno(rno);
		return session.delete(REPLY_MAPPER+".deleteReply", reply);
	}

}
