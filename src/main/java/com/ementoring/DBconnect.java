package com.ementoring;

import java.sql.*;
import java.util.*;

public class DBconnect {
	
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private String dbuser = "selab";
	private String dbpassword = "ementoring";
	private String DBname = "ementoring";

	public DBconnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DBname, dbuser, dbpassword);
			System.out.println("Connected to DB" + DBname);
			st = con.createStatement();
		} catch(Exception ex) {
			System.out.println("Error: " + ex);
		}
	}
	
	public ArrayList<User> getAllUsers() throws SQLException {
		ArrayList<User> userList = new ArrayList<>();
		try {
			String query = "SELECT * from Users";
			rs = st.executeQuery(query);
			System.out.println("Records from User table Fetched :");
			while(rs.next()) {
				User user = new User(rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getInt("type"));
				userList.add(user);
			}
		} catch(Exception ex) {
			System.out.println("error: " + ex);
		} finally {
	        if(rs != null) rs.close();
	    }
		return userList;
	}
	
	public void saveUser(User user) throws SQLException {
		String query = "INSERT INTO `Users` (`name`, `email`, `password`, `type`) VALUES ('" + user.getName() + "', '" + user.getEmail() + "', '" + user.getPassword() + "', '" + user.getUserType() + "')";
		int status = st.executeUpdate(query);
		System.out.println("User saved with status: " + status);
	}
	
	public ArrayList<User> getUserInfo(String email) throws SQLException {
		ArrayList<User> userList = new ArrayList<>();
		try {
			String query = "SELECT * from Users WHERE email LIKE '" + email + "'";
			rs = st.executeQuery(query);
			System.out.println("Records from User table Fetched :");
			while(rs.next()) {
				User user = new User(rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getInt("type"));
				userList.add(user);
			}
		} catch(Exception ex) {
			System.out.println("error: " + ex);
		} finally {
	        if(rs != null) rs.close();
	    }
		return userList;
	}
}
