package edu.spring.myboard.domain;

import java.util.Date;

public class Board {
	private int bno;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int fileCheck;
	
	public Board() {
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getFileCheck() {
		return fileCheck;
	}

	public void setFileCheck(int fileCheck) {
		this.fileCheck = fileCheck;
	}

	@Override
	public String toString() {
		return "Board [bno=" + bno + ", title=" + title + ", writer=" + writer + ", content=" + content + ", regDate="
				+ regDate + ", fileCheck=" + fileCheck + "]";
	}
	
}
