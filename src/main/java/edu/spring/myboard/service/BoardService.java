package edu.spring.myboard.service;

import java.util.ArrayList;
import java.util.List;

import edu.spring.myboard.domain.Board;
import edu.spring.myboard.domain.File;

public interface BoardService {

	int insert(Board board, ArrayList<File> fileList);
	List<Board> selectPage(int page);
	int selectBoardListCnt();
	Board selectBno(int bno);
}
