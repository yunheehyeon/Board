package edu.spring.myboard.persistence;

import java.util.List;

import edu.spring.myboard.domain.Board;

public interface BoardDao {

	int insert(Board board);
	Board findBno();
	int updateBoardCheck(Board board);
	
	List<Board> selectPage(int page);
	int selectBoardCount();

	Board selectBno(int bno);
	
	int deleteBoard(int bno);
}
