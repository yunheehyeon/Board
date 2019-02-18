package edu.spring.myboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.spring.myboard.domain.File;
import edu.spring.myboard.persistence.FileDao;

@Service
public class FileServiceImple implements FileService {

	@Autowired private FileDao fileDao;
	
	public int createFile(List<File> fileList, int bno) {
		return fileDao.insert(fileList, bno);
	}

	public List<File> selectFileByBno(int bno) {
		
		return fileDao.selectFileByBno(bno);
	}

}
