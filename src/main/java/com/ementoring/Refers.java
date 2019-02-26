package com.ementoring;

public class Refers {
	
	private int uid;
	private String uname;
	private String referTo;
	private String referByEmail;
	private String referByName;
	
	public Refers(int uid, String uname, String referTo, String referByEmail, String referByName) {
		this.setUid(uid);
		this.setUname(uname);
		this.setReferTo(referTo);
		this.setReferByEmail(referByEmail);
		this.setReferByName(referByName);
	}

	public String getReferByName() {
		return referByName;
	}

	public void setReferByName(String referByName) {
		this.referByName = referByName;
	}

	public String getReferByEmail() {
		return referByEmail;
	}

	public void setReferByEmail(String referByEmail) {
		this.referByEmail = referByEmail;
	}

	public String getReferTo() {
		return referTo;
	}

	public void setReferTo(String referTo) {
		this.referTo = referTo;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
}
