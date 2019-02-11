package com.ementoring;

public class Answer {
	private int aid;
	private String answer;
	private int qid;
	private String writtenAt;
	private String writer;
	private String writerEmail;
	
	public Answer(int aid, int qid, String answer, String writer, String writerEmail) {
		this.aid = aid;
		this.qid = qid;
		this.answer = answer;
		this.writer = writer;
		this.writerEmail = writerEmail;
	}
	
	public int getAid() {
		return aid;
	}
	
	public void setAid(int aid) {
		this.aid = aid;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public int getQid() {
		return qid;
	}
	
	public void setQid(int qid) {
		this.qid = qid;
	}
	
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writerEmail) {
		this.writer = writerEmail;
	}
	
	public String getWrittenAt() {
		return writtenAt;
	}

	public void setWrittenAt(String writtenAt) {
		this.writtenAt = writtenAt;
	}

	public String getWriterEmail() {
		return writerEmail;
	}

	public void setWriterEmail(String writerEmail) {
		this.writerEmail = writerEmail;
	}
}
