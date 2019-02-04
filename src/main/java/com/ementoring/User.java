package com.ementoring;

public class User {
	private String name;
	private String email;
	private String password;
	private String role;
	
	public User(String name, String email, String password, String role) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.role = role;
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
	
//	public void setRole(String newRole) {
//		role = newRole;
//	}
	
	public String getRole() {
		return role;
	}
	
	
}
