package edu.spring.myboard.domain;

public class File {
	private int fno;
	private int bno;
	private String filePath;
	private String fileName;
	private String fileSize;
		
	public File() {
	}

	public File(int fno, int bno, String filePath, String fileName, String fileSize) {
		this.fno = fno;
		this.bno = bno;
		this.filePath = filePath;
		this.fileName = fileName;
		this.fileSize = fileSize;
	}

	public int getFno() {
		return fno;
	}

	public void setFno(int fno) {
		this.fno = fno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
		
	
}
