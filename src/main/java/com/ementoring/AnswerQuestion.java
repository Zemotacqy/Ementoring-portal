package com.ementoring;

public class AnswerQuestion {
	private int qid;
	private String askerEmail;
	private String question;
	private String createdAt;
	private int claps;
	private int aid;
	private String answer;
	private String writerEmail;
	private String writtenAt;
	private String name;
	private String role;
	
	public AnswerQuestion(int qid, String askerEmail, String question, int claps, int aid, String answer, String writerEmail, String name, String role) {
		this.setAid(aid);
		this.setQid(qid);
		this.setAskerEmail(askerEmail);
		this.setQuestion(question);
		this.setClaps(claps);
		this.setAnswer(answer);
		this.setWriterEmail(writerEmail);
		this.setName(name);
		this.setRole(role);
	}

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public String getAskerEmail() {
		return askerEmail;
	}

	public void setAskerEmail(String askerEmail) {
		this.askerEmail = askerEmail;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public int getClaps() {
		return claps;
	}

	public void setClaps(int claps) {
		this.claps = claps;
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

	public String getWriterEmail() {
		return writerEmail;
	}

	public void setWriterEmail(String writerEmail) {
		this.writerEmail = writerEmail;
	}

	public String getWrittenAt() {
		return writtenAt;
	}

	public void setWrittenAt(String writtenAt) {
		this.writtenAt = writtenAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
