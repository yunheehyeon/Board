package edu.spring.myboard.persistence;

import edu.spring.myboard.domain.Board;

public interface BoardDao {

	int insert(Board board);
	Board findBno();
	int updateBoardCheck(Board board);
	
}
