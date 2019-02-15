package edu.spring.myboard.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public List<Board> selectPage(int page) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("start", page * 10);		

		return session.selectList(BOARD_MAPPER+".select", params);
	}

	public int selectBoardCount() {
		return session.selectOne(BOARD_MAPPER+".selectCount");
	}

	public Board selectBno(int bno) {
		Board board = new Board();
		board.setBno(bno);
		return session.selectOne(BOARD_MAPPER+".selectBno", board);
	}

}
