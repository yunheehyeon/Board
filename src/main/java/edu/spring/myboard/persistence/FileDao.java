package edu.spring.myboard.persistence;

import java.util.ArrayList;

import edu.spring.myboard.domain.File;

public interface FileDao {

	int insert(ArrayList<File> fileList);
}
