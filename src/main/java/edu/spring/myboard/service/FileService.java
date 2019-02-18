package edu.spring.myboard.service;

import java.util.List;

import edu.spring.myboard.domain.File;

public interface FileService {

	int createFile(List<File> fileList, int bno);
	List<File> selectFileByBno(int bno);
}
