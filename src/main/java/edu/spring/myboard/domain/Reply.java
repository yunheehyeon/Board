package edu.spring.myboard.domain;

import java.util.Date;

public class Reply {
	private int rno;
	private int bno;
	private String content;
	private String writer;
	private Date regDate;
	
	
	public Reply() {
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


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Reply [rno=" + rno + ", bno=" + bno + ", content=" + content + ", writer=" + writer + ", regDate="
				+ regDate + "]";
	}
	
}
