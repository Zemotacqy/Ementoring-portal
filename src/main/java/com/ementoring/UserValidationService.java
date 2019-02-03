package com.ementoring;

import java.sql.*;
import java.util.*;

public class UserValidationService {
	
	private DBconnect db = new DBconnect();
	
	public boolean userFound(String email, String password) throws SQLException {
		ArrayList<User> users = db.getAllUsers();
		for(int i=0;i< users.size();i++) {
			if(users.get(i).getEmail().equals(email) && users.get(i).getPassword().equals(password)) return true;
		}
		return false;
	}
	
	public boolean userFound(String email) throws SQLException {
		ArrayList<User> users = db.getAllUsers();
		for(int i=0;i< users.size();i++) {
			if(users.get(i).getEmail().equals(email)) return true;
		}
		return false;
	}
	
}
