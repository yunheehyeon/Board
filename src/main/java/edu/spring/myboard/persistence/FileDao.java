package edu.spring.myboard.persistence;

import java.util.List;

import edu.spring.myboard.domain.File;

public interface FileDao {

	int insert(List<File> fileList, int bno);
	
	List<File> selectFileByBno(int bno);
	
}
