package edu.spring.myboard.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.myboard.domain.Board;

@Repository
public class BoardDaoImple implements BoardDao {

	private static final String BOARD_MAPPER = "edu.spring.myboard.mappers.BoardMapper";

	@Autowired private SqlSession session;
	
	public int insert(Board board) {
		board.setFileCheck(2);
		return session.insert(BOARD_MAPPER + ".createBoard", board);
	}

	public Board findBno() {
		return session.selectOne(BOARD_MAPPER+".findBno");
	}

	public int updateBoardCheck(Board board) {
		return session.update(BOARD_MAPPER+".updateCheck", board);
	}

}
