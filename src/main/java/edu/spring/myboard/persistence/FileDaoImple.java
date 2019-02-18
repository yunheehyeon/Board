package edu.spring.myboard.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.spring.myboard.domain.Board;
import edu.spring.myboard.domain.File;

@Repository
public class FileDaoImple implements FileDao {

	private static final String FILE_MAPPER = "edu.spring.myboard.mappers.FileMapper";

	@Autowired private SqlSession session;
	
	public int insert(List<File> fileList, int bno) {
		int result = 0;
		for(File f : fileList) {
			f.setBno(bno);
			result = session.insert(FILE_MAPPER+".createFile", f);
		}
		return result;
	}

	public List<File> selectFileByBno(int bno) {
		File file = new File();
		file.setBno(bno);
		return session.selectList(FILE_MAPPER+".selectByBno", file);
	}

}
