package com.ementoring;

public class User {
	private String name;
	private String email;
	private String password;
	private int userType;
	
	public User(String name, String email, String password, int userType) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.userType = userType;
	}
	
	/**
	 * Since we don't require any set methods
	 * It is commented!
	 */
	
//	public void setName(String newName) {
//		name = newName;
//	}
	
	public String getName() {
		return name;
	}
	
//	public void setEmail(String newEmail) {
//		email = newEmail;
//	}
	
	public String getEmail() {
		return email;
	}
	
//	public void setPassword(String newPassword) {
//		password = newPassword;
//	}
	
	public String getPassword() {
		return password;
	}
	
//	public void setUserType(int newUserType) {
//		userType = newUserType;
//	}
	
	public int getUserType() {
		return userType;
	}
	
	
}
