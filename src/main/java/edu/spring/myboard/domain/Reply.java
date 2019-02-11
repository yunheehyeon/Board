package edu.spring.myboard.domain;

import java.util.Date;

public class Reply {
	private int rno;
	private int bno;
	private int content;
	private Date regDate;
	
	
	
	public Reply() {
	}

	public Reply(int rno, int bno, int content, Date regDate) {
		this.rno = rno;
		this.bno = bno;
		this.content = content;
		this.regDate = regDate;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
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
