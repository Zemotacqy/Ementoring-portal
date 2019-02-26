package com.ementoring;

public class Universities {
	private int uid;
	private String uname;
	
	public Universities (int uid, String uname) {
		this.uid = uid;
		this.setUname(uname);
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
