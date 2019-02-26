package com.ementoring;

public class Messages {
	
	private int mid;
	private String sentBy;
	private String sentTo;
	private String message;
	
	public Messages(int mid, String sentBy, String sentTo, String message) {
		this.mid = mid;
		this.setSentBy(sentBy);
		this.setSentTo(sentTo);
		this.setMessage(message);
	}

	public String getSentBy() {
		return sentBy;
	}

	public void setSentBy(String sentBy) {
		this.sentBy = sentBy;
	}

	public String getSentTo() {
		return sentTo;
	}

	public void setSentTo(String sentTo) {
		this.sentTo = sentTo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}
}
