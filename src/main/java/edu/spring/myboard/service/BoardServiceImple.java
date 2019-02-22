package edu.spring.myboard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.myboard.domain.Board;
import edu.spring.myboard.domain.File;
import edu.spring.myboard.persistence.BoardDao;

@Service
public class BoardServiceImple implements BoardService {

	@Autowired BoardDao boardDao;
	@Autowired FileService fileService;
	
	public int insert(Board board, ArrayList<File> fileList) {
		
		int result = boardDao.insert(board);
		if(result == 1) {
			Board temp = boardDao.findBno();
			System.out.println(temp.toString());
			if(fileList.size()>0) {
				temp.setFileCheck(1);
				boardDao.updateBoardCheck(temp);
								
				fileService.createFile(fileList, temp.getBno());
			}else {
				temp.setFileCheck(0);
				boardDao.updateBoardCheck(temp);
			}
		}
		return result;
	}

	public List<Board> selectPage(int page) {
		return boardDao.selectPage(page);
	}

	public int selectBoardListCnt() {
		
		return boardDao.selectBoardCount();
	}

	public Board selectBno(int bno) {
		return boardDao.selectBno(bno);
	}

	public int delectboard(int bno) {
		return boardDao.deleteBoard(bno);
	}

}
