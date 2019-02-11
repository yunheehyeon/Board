package edu.spring.myboard.domain;

import java.util.Date;

public class Board {
	private int bno;
	private int content;
	private Date regDate;
	
	public Board() {
	}

	public Board(int bno, int content, Date regDate) {
		this.bno = bno;
		this.content = content;
		this.regDate = regDate;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public int getContent() {
		return content;
	}

	public void setContent(int content) {
		this.content = content;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}
