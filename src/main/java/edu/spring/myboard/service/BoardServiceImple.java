package edu.spring.myboard.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.myboard.domain.Board;
import edu.spring.myboard.domain.File;
import edu.spring.myboard.persistence.BoardDao;

@Service
public class BoardServiceImple implements BoardService {

	@Autowired BoardDao boardDao;
	
	public int insert(Board board, ArrayList<File> fileList) {
		
		int result = boardDao.insert(board);
		if(result == 1) {
			Board temp = boardDao.findBno();
			System.out.println(temp.toString());
			if(fileList.size()>0) {
				temp.setFileCheck(1);
				System.out.println(temp.toString());
				boardDao.updateBoardCheck(temp);
			}else {
				temp.setFileCheck(0);
				System.out.println(temp.toString());
				boardDao.updateBoardCheck(temp);
			}
		}
		return result;
	}

}
